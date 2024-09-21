package com.lab.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 用户表
* @TableName user
*/
@Data
public class User implements Serializable {

    /**
    * 主键id
    */
    @ApiModelProperty("主键id")
    private Integer id;
    /**
    * 用户名
    */
    @ApiModelProperty("用户名")
    private String name;
    /**
    * 学号
    */
    @ApiModelProperty("学号")
    private String number;
    /**
    * 密码
    */
    @ApiModelProperty("密码")
    private String password;
    /**
    * 电子邮箱地址
    */
    @ApiModelProperty("电子邮箱地址")
    private String email;
    /**
    * 是否为导师，0不是，1是
    */
    
    @ApiModelProperty("是否为导师，0不是，1是")
    private Integer isTutor;

}
