package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskUpdateDto {
    @ApiModelProperty("任务主键ID")
    private Integer id;
    @ApiModelProperty("任务名字")
    private String taskName;
    @ApiModelProperty("任务已分配的学生id")
    private String taskAssignedUserId;
    @ApiModelProperty("任务类型id")
    private Integer taskTypeId;
    @ApiModelProperty("任务标签id")
    private Integer taskTagId;
    @ApiModelProperty("更新用户ID")
    private Integer updateUserId;
    @ApiModelProperty("任务结束时间")
    private String endTime;
}
