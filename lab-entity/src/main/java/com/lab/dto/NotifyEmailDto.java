package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class NotifyEmailDto {
    @ApiModelProperty("任务已分配的学生id")
    private String taskAssignedUserId;

    @ApiModelProperty("任务名")
    private List<String> taskName;

    @ApiModelProperty("任务主键ID")
    private List<Integer> id;

    @ApiModelProperty("通知内容")
    private List<String> content;

    @ApiModelProperty("邮件地址")
    private List<String> email;

    @ApiModelProperty("邮件类型")
    private Integer emailType;

    @ApiModelProperty("修改前已分配的用户ID，仅用于修改通知")
    private String beforeAssignedUserId;
}
