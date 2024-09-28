package com.lab.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaskSingleVo {
    @ApiModelProperty("任务名字")
    private String taskName;

    @ApiModelProperty("任务已分配的学生id")
    private String taskAssignedUserId;
    @ApiModelProperty("任务已分配的学生姓名")
    private List<String> taskAssignedUserName;

    @ApiModelProperty("任务类型id")
    private Integer taskTypeId;
    @ApiModelProperty("任务类型名字")
    private String taskTypeName;

    @ApiModelProperty("任务标签id")
    private Integer taskTagId;
    @ApiModelProperty("任务标签名字")
    private String taskTagName;

    @ApiModelProperty("创建用户ID")
    private Integer createUserId;
    @ApiModelProperty("创建用户名字")
    private String createUserName;

    @ApiModelProperty("更新用户ID")
    private Integer updateUserId;
    @ApiModelProperty("更新用户名字")
    private String updateUserName;

    @ApiModelProperty("开始时间")
    private String beginTime;
    @ApiModelProperty("更新时间")
    private String updateTime;
    @ApiModelProperty("任务结束时间")
    private String endTime;
}
