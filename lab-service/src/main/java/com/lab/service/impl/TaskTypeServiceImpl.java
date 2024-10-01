package com.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.lab.exception.BusinessException;
import com.lab.mapper.TaskMapper;
import com.lab.mapper.TaskTypeMapper;
import com.lab.response.Page;
import com.lab.service.TaskTypeService;
import com.lab.utils.PageUtil;
import com.lab.vo.NameAndCodeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskTypeServiceImpl implements TaskTypeService {
    @Resource
    private TaskTypeMapper taskTypeMapper;
    @Resource
    private TaskMapper taskMapper;

    @Override
    public Integer add (String name) {
        checkNameExist(name);

        return taskTypeMapper.add(name);
    }

    /*
     * 如果有任务关联了这个类型，则不能删除，提示用户
     * */
    @Override
    public Integer delete (List<Integer> ids) {
        // 传入的是task_type的ID，需要根据它查出是否有关联的任务
        int count = taskMapper.getTaskTypeRelativeWithTaskTag(ids);
        if (count > 0) {
            throw new BusinessException("有任务关联类型，无法删除！");
        }
        return taskTypeMapper.delete(ids);
    }

    @Override
    public boolean update (Integer id, String name) {
        checkNameExist(name);
        return taskTypeMapper.update(id, name);
    }

    @Override
    public Page<NameAndCodeVo> list (int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NameAndCodeVo> list = taskTypeMapper.list();
        return PageUtil.toPage(list);
    }

    private void checkNameExist (String name) {
        int count = taskTypeMapper.getCountByName(name);
        if (count > 0) {
            throw new BusinessException("该标签名字已存在！");
        }
    }
}
