package com.lab.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NotifyListVo {
    @ApiModelProperty("通知记录的主键ID")
    private Integer id;

    @ApiModelProperty("通知内容")
    private String content;

    @ApiModelProperty("通知创建时间")
    private String createTime;

    @ApiModelProperty("是否查看过该通知")
    private Integer isLook;

}
