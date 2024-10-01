package com.lab.service.impl;

import com.lab.response.Page;
import com.lab.service.TaskTagService;
import com.lab.vo.NameAndCodeVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTagServiceImpl implements TaskTagService {
    @Override
    public Integer add (String name) {
        return 0;
    }

    @Override
    public Integer delete (List<Integer> ids) {
        return 0;
    }

    @Override
    public boolean update (Integer id, String name) {
        return false;
    }

    @Override
    public Page<NameAndCodeVo> list (int pageNum, int pageSize) {
        return null;
    }
}
