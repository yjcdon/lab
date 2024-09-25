package com.lab.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSingleVo {
    @ApiModelProperty("主键ID，不做展示")
    private Integer id;
    @ApiModelProperty("账号，学生展示学号，老师展示姓名的拼音")
    private String account;
    @ApiModelProperty("真实姓名")
    private String name;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("是否为导师")
    private Integer isTutor;
    @ApiModelProperty("性别")
    private String sex;
}
