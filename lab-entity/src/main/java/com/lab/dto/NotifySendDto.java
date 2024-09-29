package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
* 增删改后，发送给 mq 的结构
* */

@Data
public class NotifySendDto implements Serializable {
    @ApiModelProperty("任务主键ID")
    private List<Integer> id;
    
    @ApiModelProperty("任务名")
    private String taskName;
    
    @ApiModelProperty("已分配用户ID")
    private String taskAssignedUserId;

    @ApiModelProperty("任务分类id")
    private Integer taskTypeId;

    @ApiModelProperty("任务标签id")
    private Integer taskTagId;

    @ApiModelProperty("更新用户id")
    private Integer updateUserId;

    @ApiModelProperty("开始时间")
    private String beginTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("通知类型，1新增任务，2删除任务，3修改任务")
    private Integer notifyType;

    @ApiModelProperty("修改前已分配的用户ID，仅用于修改通知")
    private String beforeAssignedUserId;
}
