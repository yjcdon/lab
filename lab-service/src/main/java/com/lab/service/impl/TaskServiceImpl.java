package com.lab.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lab.constant.MqConstant;
import com.lab.dto.NotifySendDto;
import com.lab.dto.TaskAddDto;
import com.lab.dto.TaskListDto;
import com.lab.dto.TaskUpdateDto;
import com.lab.mapper.TaskMapper;
import com.lab.mapper.UserMapper;
import com.lab.response.Page;
import com.lab.service.TaskService;
import com.lab.utils.PageUtil;
import com.lab.utils.UserUtil;
import com.lab.vo.TaskListVo;
import com.lab.vo.TaskSingleVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public Integer add (TaskAddDto taskAddDto) {
        // 得到SQL影响行数
        Integer count = taskMapper.add(taskAddDto);

        // 如果插入成功，则异步发送通知
        if (count != null && count > 0) {
            sendNotifyForAdd(taskAddDto);
        }

        return count;
    }

    @Async
    protected void sendNotifyForAdd (TaskAddDto taskAddDto) {
        NotifySendDto dto = new NotifySendDto();
        dto.setId(Collections.singletonList(taskAddDto.getId()));
        dto.setNotifyType(1);

        BeanUtil.copyProperties(taskAddDto, dto, "id");

        rabbitTemplate.convertAndSend(MqConstant.EXCHANGE_LAB, MqConstant.ROUTING_KEY_ADD, dto);
    }

    @Override
    public Integer delete (List<Integer> ids) {
        Integer count = taskMapper.delete(ids);

        // 删除成功，发送消息
        if (count != null && count > 0) {
            sendNotifyForDelete(ids, count);
        }
        return count;
    }

    @Async
    protected void sendNotifyForDelete (List<Integer> ids, Integer count) {
        NotifySendDto dto = new NotifySendDto();
        // 在监听函数中，需要根据任务主键ID查询已分配用户ID
        dto.setId(ids);
        dto.setNotifyType(2);
        rabbitTemplate.convertAndSend(MqConstant.EXCHANGE_LAB, MqConstant.ROUTING_KEY_DELETE, dto);
    }

    @Override
    public boolean update (TaskUpdateDto taskUpdateDto) {
        // 修改任务可能会增减用户，所以这里提前查出来原来分配的ID
        String beforeIds = taskMapper.getTaskAssignedUserIds(Collections.singletonList(taskUpdateDto.getId())).get(0);

        boolean isSuccess = taskMapper.update(taskUpdateDto);
        if (isSuccess) {
            sendNotifyForUpdate(taskUpdateDto, beforeIds);
        }

        return isSuccess;
    }

    @Async
    protected void sendNotifyForUpdate (TaskUpdateDto taskUpdateDto, String beforeIds) {
        NotifySendDto dto = new NotifySendDto();
        // 设置任务主键ID
        dto.setId(Collections.singletonList(taskUpdateDto.getId()));
        dto.setNotifyType(3);
        dto.setTaskAssignedUserId(taskUpdateDto.getTaskAssignedUserId());
        dto.setBeforeAssignedUserId(beforeIds);

        rabbitTemplate.convertAndSend(MqConstant.EXCHANGE_LAB, MqConstant.ROUTING_KEY_UPDATE, dto);
    }

    @Override
    public TaskSingleVo getById (Integer id) {
        // 得到主要信息
        TaskSingleVo vo = taskMapper.getById(id);

        if (vo != null) {
            // 得到任务已分配的学生姓名
            String[] ids = vo.getTaskAssignedUserId().split(",");

            List<Integer> assignedUserIds = new ArrayList<>(ids.length);
            for (String id2 : ids) {
                assignedUserIds.add(Integer.parseInt(id2));
            }
            List<String> names = userMapper.getNamesByIds(assignedUserIds);
            vo.setTaskAssignedUserName(names);

            // 得到创建者和更新者的名字
            String createName = userMapper.getNameById(vo.getCreateUserId());
            if (vo.getCreateUserId().equals(vo.getUpdateUserId())) {
                vo.setCreateUserName(createName);
                vo.setUpdateUserName(createName);
            } else {
                vo.setCreateUserName(createName);
                vo.setUpdateUserName(userMapper.getNameById(vo.getUpdateUserId()));
            }
        }

        return vo;
    }

    @Override
    public Page<TaskListVo> list (TaskListDto taskListDto) {
        List<TaskListVo> list = taskMapper.list(taskListDto);
        if (!list.isEmpty()) {
            for (TaskListVo vo : list) {
                // 得到任务已分配的学生姓名
                String[] ids = vo.getTaskAssignedUserId().split(",");

                List<Integer> assignedUserIds = new ArrayList<>(ids.length);
                for (String id : ids) {
                    assignedUserIds.add(Integer.parseInt(id));
                }
                List<String> names = userMapper.getNamesByIds(assignedUserIds);
                vo.setTaskAssignedUserName(names);

                // 得到创建者的名字
                String createName = userMapper.getNameById(vo.getCreateUserId());
                vo.setCreateUserName(createName);
            }
        }

        // 判断是不是学生
        Integer isTutor = UserUtil.get().getIsTutor();
        // 是学生，则只返回部分信息
        if (isTutor == 0) {
            Integer userId = UserUtil.get().getUserId();
            // 过滤出 task_assigned_user_id 中包含 userId 的记录
            list = list.stream()
                    .filter(task -> {
                        String[] assignedUserIds = task.getTaskAssignedUserId().split(",");
                        for (String id : assignedUserIds) {
                            if (Integer.parseInt(id) == userId) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }

        return PageUtil.toPage(list);
    }


}
