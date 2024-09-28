package com.lab.service;

import com.lab.dto.TaskAddDto;
import com.lab.dto.TaskListDto;
import com.lab.dto.TaskUpdateDto;
import com.lab.response.Page;
import com.lab.vo.TaskListVo;
import com.lab.vo.TaskSingleVo;

import java.util.List;

/**
 * Author: 梁雨佳
 * Date: 2024/9/28 12:00:02
 * Description:
 */
public interface TaskService {
    Integer add (TaskAddDto taskAddDto);

    Integer delete (List<Integer> ids);

    boolean update (TaskUpdateDto taskUpdateDto);

    Page<TaskListVo> list (TaskListDto taskListDto);

    TaskSingleVo getById (Integer id);

}
