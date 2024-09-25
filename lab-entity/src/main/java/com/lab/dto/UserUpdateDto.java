package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserUpdateDto {
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("真实名字")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("是否为导师")
    private Integer isTutor;
}
