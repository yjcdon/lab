package com.lab.service.impl;

import com.lab.dto.TaskAddDto;
import com.lab.dto.TaskListDto;
import com.lab.dto.TaskUpdateDto;
import com.lab.mapper.TaskMapper;
import com.lab.mapper.UserMapper;
import com.lab.response.Page;
import com.lab.service.TaskService;
import com.lab.utils.PageUtil;
import com.lab.vo.TaskListVo;
import com.lab.vo.TaskSingleVo;
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

        if(vo!=null){
            // 得到任务已分配的学生姓名
            String[] names = vo.getTaskAssignedUserId().split(",");
            List<String> taskAssignedUserName = Arrays.stream(names).collect(Collectors.toList());
            vo.setTaskAssignedUserName(taskAssignedUserName);

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
        if(!list.isEmpty()){
            for (TaskListVo vo : list) {
                // 得到任务已分配的学生姓名
                String[] names = vo.getTaskAssignedUserId().split(",");
                List<String> taskAssignedUserName = Arrays.stream(names).collect(Collectors.toList());
                vo.setTaskAssignedUserName(taskAssignedUserName);

                // 得到创建者的名字
                String createName = userMapper.getNameById(vo.getCreateUserId());
                vo.setCreateUserName(createName);
            }
        }

        return PageUtil.toPage(list);
    }


}
