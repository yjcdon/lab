package com.lab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NotifyListDto {
    @ApiModelProperty("当前用户ID，前端不用传")
    private Integer userId;

    @ApiModelProperty("当前页码")
    private Integer pageNum;

    @ApiModelProperty("当前页大小")
    private Integer pageSize;
}
