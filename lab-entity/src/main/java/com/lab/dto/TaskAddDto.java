package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskAddDto {

    @ApiModelProperty("任务名字")
    private String taskName;
    @ApiModelProperty("任务已分配的学生id")
    private String taskAssignedUserId;
    @ApiModelProperty("任务类型id")
    private Integer taskTypeId;
    @ApiModelProperty("任务标签id")
    private Integer taskTagId;
    @ApiModelProperty("创建用户ID")
    private Integer createUserId;
    @ApiModelProperty("更新用户ID")
    private Integer updateUserId;
    @ApiModelProperty("开始时间")
    private String beginTime;
    @ApiModelProperty("任务结束时间")
    private String endTime;

}
