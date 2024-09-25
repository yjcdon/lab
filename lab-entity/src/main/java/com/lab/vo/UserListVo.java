package com.lab.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserListVo {
    @ApiModelProperty("主键ID，不做展示")
    private Integer id;
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("真实姓名")
    private String name;
    @ApiModelProperty("性别")
    private String sex;
}
