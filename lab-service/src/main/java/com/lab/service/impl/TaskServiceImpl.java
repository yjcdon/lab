package com.lab.service.impl;

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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Integer add (TaskAddDto taskAddDto) {
        return taskMapper.add(taskAddDto);
    }

    @Override
    public Integer delete (List<Integer> ids) {
        return taskMapper.delete(ids);
    }

    @Override
    public boolean update (TaskUpdateDto taskUpdateDto) {
        return taskMapper.update(taskUpdateDto);
    }

    @Override
    public TaskSingleVo getById (Integer id) {
        // 得到主要信息
        TaskSingleVo vo = taskMapper.getById(id);

        if (vo != null) {
            // 得到任务已分配的学生姓名
            String[] ids = vo.getTaskAssignedUserId().split(",");
            List<String> names = userMapper.getNamesByIds(ids);
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
                List<String> names = userMapper.getNamesByIds(ids);
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
