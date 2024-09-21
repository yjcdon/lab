package com.lab.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 任务通知表
* @TableName notify
*/
@Data
public class Notify implements Serializable {

    /**
    * 主键id
    */
    @ApiModelProperty("主键id")
    private Integer id;
    /**
    * 通知内容
    */
    @ApiModelProperty("通知内容")
    private String content;
    /**
    * 被通知人的id
    */
    @ApiModelProperty("被通知人的id")
    private Integer userId;
    /**
    * 用户是否查看了该通知，0未查看，1已查看
    */
    @ApiModelProperty("用户是否查看了该通知，0未查看，1已查看")
    private Integer isLook;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 是否删除，0未删除，1已删除
    */
    @ApiModelProperty("是否删除，0未删除，1已删除")
    private Integer isDelete;
}
