package com.lab.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NameAndCodeVo {
    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("id或者code")
    private Integer code;
}
