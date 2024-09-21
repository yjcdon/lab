package com.lab.service.impl;

import com.lab.dto.UserListDto;
import com.lab.dto.UserLoginDto;
import com.lab.dto.UserRegisterDto;
import com.lab.dto.UserUpdateDto;
import com.lab.mapper.UserMapper;
import com.lab.response.Page;
import com.lab.service.UserService;
import com.lab.vo.UserListVo;
import com.lab.vo.UserSingleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public String login (UserLoginDto userLoginDto) {
        return "";
    }

    @Override
    public String register (UserRegisterDto userRegisterDto) {
        return "";
    }

    @Override
    public UserSingleVo getById (Integer id) {
        return null;
    }

    @Override
    public Page<UserListVo> list (UserListDto userListDto) {
        return null;
    }

    @Override
    public String update (UserUpdateDto userUpdateDto) {
        return "";
    }

    @Override
    public String delete (List<Integer> ids) {
        return "";
    }
}
