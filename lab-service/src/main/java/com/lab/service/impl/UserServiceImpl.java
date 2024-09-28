package com.lab.service.impl;

import cn.hutool.core.lang.UUID;
import com.github.pagehelper.PageHelper;
import com.lab.constant.RedisConstant;
import com.lab.dto.*;
import com.lab.mapper.UserMapper;
import com.lab.response.Page;
import com.lab.service.UserService;
import com.lab.utils.PageUtil;
import com.lab.utils.UserUtil;
import com.lab.vo.UserListVo;
import com.lab.vo.UserSingleVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String login (UserLoginDto userLoginDto) {
        boolean isSuccess = userMapper.isExist(userLoginDto);
        // 整合Redis
        if (isSuccess) {
            String uuid = UUID.randomUUID().toString(true);
            String key = RedisConstant.USER_LOGIN + uuid;
            UserAuthDto userAuthDto = userMapper.getIdByNameAndPassword(userLoginDto);
            redisTemplate.opsForValue().set(key, userAuthDto);
            redisTemplate.expire(key, 7L, TimeUnit.DAYS);
            return uuid;
        }

        return "";
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
        return isSuccess;
    }

    @Override
    public boolean delete (List<Integer> ids) {
        boolean isSuccess = userMapper.deleteByIds(ids);
        return isSuccess;
    }

    @Override
    public String logout (HttpServletRequest request) {
        String key = RedisConstant.USER_LOGIN + request.getHeader("x-auth-token");
        redisTemplate.delete(key);
        UserUtil.remove();
        return "退出成功";
    }
}
