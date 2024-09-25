package com.lab.dto;

import lombok.Data;

import java.io.Serializable;

/*
* 通过isTutor来进行权限控制
* */
@Data
public class UserAuthDto implements Serializable {
    private Integer userId;
    private Integer isTutor;
}
