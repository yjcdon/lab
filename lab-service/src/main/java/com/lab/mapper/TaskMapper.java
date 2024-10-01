package com.lab.mapper;

import com.lab.dto.TaskAddDto;
import com.lab.dto.TaskListDto;
import com.lab.dto.TaskUpdateDto;
import com.lab.vo.TaskListVo;
import com.lab.vo.TaskSingleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: 梁雨佳
 * Date: 2024/9/28 12:00:29
 * Description:
 */
public interface TaskMapper {
    Integer add (@Param("dto") TaskAddDto taskAddDto);

    Integer delete (@Param("ids") List<Integer> ids);

    boolean update (@Param("dto") TaskUpdateDto taskUpdateDto);

    TaskSingleVo getById (@Param("id") Integer id);

    List<TaskListVo> list (@Param("dto") TaskListDto taskListDto);

    List<String> getTaskNamesByIds (@Param("ids") List<Integer> taskIds);

    List<String> getTaskAssignedUserIds (@Param("ids") List<Integer> taskIds);

    int getTaskTagRelativeWithTaskTag (@Param("ids") List<Integer> ids);

    int getTaskTypeRelativeWithTaskTag (@Param("ids") List<Integer> ids);

    int getCountByName (@Param("name") String taskName);
}
