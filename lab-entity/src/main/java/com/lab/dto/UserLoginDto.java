package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginDto {
    @ApiModelProperty("账号，学生输入学号，老师输入姓名的拼音")
    private String account;

    @ApiModelProperty("密码")
    private String password;
}
