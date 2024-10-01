package com.lab.service;

import com.lab.response.Page;
import com.lab.vo.NameAndCodeVo;

import java.util.List;

/**
 * Author: 梁雨佳
 * Date: 2024/9/29 21:58:00
 * Description:
 */
public interface TaskTypeService {
    Integer add (String name);

    Integer delete (List<Integer> ids);

    boolean update (Integer id, String name);

    Page<NameAndCodeVo> list (int pageNum, int pageSize);
}
