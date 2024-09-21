package com.lab.service;

import com.lab.dto.UserListDto;
import com.lab.dto.UserLoginDto;
import com.lab.dto.UserRegisterDto;
import com.lab.dto.UserUpdateDto;
import com.lab.response.Page;
import com.lab.vo.UserListVo;
import com.lab.vo.UserSingleVo;

import java.util.List;

/**
 * Author: 梁雨佳
 * Date: 2024/9/19 21:42:06
 * Description:
 */
public interface UserService {
    String login (UserLoginDto userLoginDto);

    String register (UserRegisterDto userRegisterDto);

    UserSingleVo getById (Integer id);

    Page<UserListVo> list (UserListDto userListDto);

    String update (UserUpdateDto userUpdateDto);

    String delete (List<Integer> ids);
}
