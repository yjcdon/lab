package com.lab.mapper;

import com.lab.dto.NotifyAddDto;
import org.apache.ibatis.annotations.Param;

/**
 * Author: 梁雨佳
 * Date: 2024/9/28 21:10:14
 * Description:
 */
public interface NotifyMapper {
    void insertForTypeAdd (@Param("dto") NotifyAddDto notifyAddDto);
}
