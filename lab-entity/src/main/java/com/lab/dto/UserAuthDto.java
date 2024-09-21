package com.lab.dto;

import lombok.Data;

/*
* 通过isTutor来进行权限控制
* */
@Data
public class UserAuthDto {
    private Integer userId;
    private Integer isTutor;
}
