package com.lab.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NotifySingleVo {
    @ApiModelProperty("通知内容")
    private String content;

    @ApiModelProperty("通知类型名字")
    private String notifyTypeName;

    @ApiModelProperty("通知创建时间")
    private String createTime;

    @ApiModelProperty("是否查看")
    private Integer isLook;
}
