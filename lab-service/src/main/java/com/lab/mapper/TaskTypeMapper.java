package com.lab.mapper;

import com.lab.vo.NameAndCodeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: 梁雨佳
 * Date: 2024/9/29 21:59:04
 * Description:
 */
public interface TaskTypeMapper {
    Integer add (@Param("name") String name);

    List<NameAndCodeVo> list ();

    Integer delete (@Param("ids") List<Integer> ids);

    boolean update (@Param("id") Integer id, @Param("name") String name);
}
