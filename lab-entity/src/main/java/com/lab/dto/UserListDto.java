package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserListDto {
    @ApiModelProperty("账号，导师可以通过学号模糊查询")
    private String account;
    @ApiModelProperty("真实姓名，导师可以通过名字模糊查询")
    private String name;
    @ApiModelProperty("是否为导师")
    private Integer isTutor;
    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("当前页数")
    private Integer pageNum;
    @ApiModelProperty("页大小")
    private Integer pageSize;
}
