package com.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.lab.exception.BusinessException;
import com.lab.mapper.TaskMapper;
import com.lab.mapper.TaskTagMapper;
import com.lab.response.Page;
import com.lab.service.TaskTagService;
import com.lab.utils.PageUtil;
import com.lab.vo.NameAndCodeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskTagServiceImpl implements TaskTagService {
    @Resource
    private TaskTagMapper taskTagMapper;
    @Resource
    private TaskMapper taskMapper;

    @Override
    public Integer add (String name) {
        checkNameExist(name);
        return taskTagMapper.add(name);
    }

    /*
     * 如果有任务关联了这个标签，则不能删除，提示用户
     * */
    @Override
    public Integer delete (List<Integer> ids) {
        // 传入的是task_tag的ID，需要根据它查出是否有关联的任务
        int count = taskMapper.getTaskTagRelativeWithTaskTag(ids);
        if (count > 0) {
            throw new BusinessException("有任务关联标签，无法删除！");
        }
        return taskTagMapper.delete(ids);
    }

    @Override
    public boolean update (Integer id, String name) {
        checkNameExist(name);
        return taskTagMapper.update(id, name);
    }

    @Override
    public Page<NameAndCodeVo> list (int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NameAndCodeVo> list = taskTagMapper.list();
        return PageUtil.toPage(list);
    }

    private void checkNameExist (String name) {
        int count = taskTagMapper.getCountByName(name);
        if (count > 0) {
            throw new BusinessException("该标签名字已存在！");
        }
    }
}
