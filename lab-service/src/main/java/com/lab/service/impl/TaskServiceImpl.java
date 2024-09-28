package com.lab.service.impl;

import com.lab.dto.TaskAddDto;
import com.lab.dto.TaskListDto;
import com.lab.dto.TaskUpdateDto;
import com.lab.response.Page;
import com.lab.service.TaskService;
import com.lab.vo.TaskListVo;
import com.lab.vo.TaskSingleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public Integer add (TaskAddDto taskAddDto) {
        return 0;
    }

    @Override
    public Integer delete (List<Integer> ids) {
        return 0;
    }

    @Override
    public boolean update (TaskUpdateDto taskUpdateDto) {
        return false;
    }

    @Override
    public Page<TaskListVo> list (TaskListDto taskListDto) {
        return null;
    }

    @Override
    public TaskSingleVo getById (Integer id) {
        return null;
    }
}
