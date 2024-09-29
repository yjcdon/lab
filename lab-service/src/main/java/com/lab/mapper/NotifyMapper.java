package com.lab.mapper;

import com.lab.dto.NotifyAddDto;
import com.lab.dto.NotifyListDto;
import com.lab.vo.NotifyListVo;
import com.lab.vo.NotifySingleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: 梁雨佳
 * Date: 2024/9/28 21:10:14
 * Description:
 */
public interface NotifyMapper {
    void insertForTypeAdd (@Param("dto") NotifyAddDto notifyAddDto);

    NotifySingleVo getById (@Param("id") Integer id);

    List<NotifyListVo> list (@Param("dto") NotifyListDto notifyListDto);

    Integer delete (@Param("ids") List<Integer> ids);

    Integer getIsNotLookCount (@Param("id") Integer userId);
}
