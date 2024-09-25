package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRegisterDto {
    @ApiModelProperty("真实姓名")
    private String name;
    @ApiModelProperty("账号，学生输入学号，老师输入姓名的拼音")
    private String account;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("是否为导师")
    private Integer isTutor;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("性别")
    private String sex;
}
