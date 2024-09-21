package com.lab.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 任务标签表
* @TableName task_tag
*/
@Data
public class TaskTag implements Serializable {

    /**
    * 主键id
    */
    @ApiModelProperty("主键id")
    private Integer id;
    /**
    * 任务标签名字
    */
    @ApiModelProperty("任务标签名字")
    private String taskTagName;


}
