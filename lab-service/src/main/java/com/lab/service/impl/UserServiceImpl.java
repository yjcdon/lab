package com.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.lab.dto.UserListDto;
import com.lab.dto.UserLoginDto;
import com.lab.dto.UserRegisterDto;
import com.lab.dto.UserUpdateDto;
import com.lab.mapper.UserMapper;
import com.lab.response.Page;
import com.lab.service.UserService;
import com.lab.utils.PageUtil;
import com.lab.vo.UserListVo;
import com.lab.vo.UserSingleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login (UserLoginDto userLoginDto) {
        boolean isSuccess = userMapper.getByNameAndPsw(userLoginDto);
        // 整合Redis
        return isSuccess;
    }

    @Override
    public boolean register (UserRegisterDto userRegisterDto) {
        boolean isSuccess = userMapper.register(userRegisterDto);
        return isSuccess;
    }

    @Override
    public UserSingleVo getById (Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public Page<UserListVo> list (UserListDto userListDto) {
        PageHelper.startPage(userListDto.getPageNum(), userListDto.getPageSize());
        List<UserListVo> list = userMapper.list(userListDto);
        return PageUtil.toPage(list);
    }

    @Override
    public boolean update (UserUpdateDto userUpdateDto) {
        boolean isSuccess = userMapper.update(userUpdateDto);
        return isSuccess ;
    }

    @Override
    public boolean delete (List<Integer> ids) {
        boolean isSuccess = userMapper.deleteByIds(ids);
        return isSuccess ;
    }
}
