package com.lab.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 任务表
* @TableName task
*/
@Data
public class Task implements Serializable {

    /**
    * 主键id
    */
    @ApiModelProperty("主键id")
    private Integer id;
    /**
    * 任务名字
    */
    @ApiModelProperty("任务名字")
    private String taskName;
    /**
    * 任务已分配的学生id，通过英文逗号分隔
    */
    @ApiModelProperty("任务已分配的学生id，通过英文逗号分隔")
    private String taskAssginedUserId;
    /**
    * 任务分类id
    */
    @ApiModelProperty("任务分类id")
    private Integer taskTypeId;
    /**
    * 任务标签id
    */
    @ApiModelProperty("任务标签id")
    private Integer taskTagId;
    /**
    * 创建人id
    */
    @ApiModelProperty("创建人id")
    private Integer createUserId;
    /**
    * 更新人id
    */
    @ApiModelProperty("更新人id")
    private Integer updateUserId;
    /**
    * 任务创建时间
    */
    @ApiModelProperty("任务创建时间")
    private Date createTime;
    /**
    * 任务更新时间
    */
    @ApiModelProperty("任务更新时间")
    private Date updateTime;
    /**
    * 任务结束时间
    */
    @ApiModelProperty("任务结束时间")
    private Date endTime;
    /**
    * 任务是否删除，0未删除，1已删除
    */
    @ApiModelProperty("任务是否删除，0未删除，1已删除")
    private Integer isDelete;

}
