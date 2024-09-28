package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class NotifyAddDto {
    @ApiModelProperty("消息具体内容")
    private String content;

    @ApiModelProperty("分配任务的用户ID")
    private List<Integer> userId;

    @ApiModelProperty("是否查看该消息")
    private Integer isLook;

    @ApiModelProperty("通知类型，1新增任务，2删除任务，3修改任务")
    private Integer notifyType;

}
