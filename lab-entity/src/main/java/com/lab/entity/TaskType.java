package com.lab.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 任务类型表
* @TableName task_type
*/
@Data
public class TaskType implements Serializable {

    /**
    * 主键id
    */
    @ApiModelProperty("主键id")
    private Integer id;
    /**
    * 任务分类名称
    */
    @ApiModelProperty("任务分类名称")
    
    private String taskTypeName;

}
