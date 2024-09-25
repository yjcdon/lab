package com.lab.service;

import com.lab.dto.UserListDto;
import com.lab.dto.UserLoginDto;
import com.lab.dto.UserRegisterDto;
import com.lab.dto.UserUpdateDto;
import com.lab.response.Page;
import com.lab.vo.UserListVo;
import com.lab.vo.UserSingleVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: 梁雨佳
 * Date: 2024/9/19 21:42:06
 * Description:
 */
public interface UserService {
    String login (UserLoginDto userLoginDto);

    boolean register (UserRegisterDto userRegisterDto);

    UserSingleVo getById (Integer id);

    Page<UserListVo> list (UserListDto userListDto);

    boolean update (UserUpdateDto userUpdateDto);

    boolean delete (List<Integer> ids);

    String logout (HttpServletRequest request);
}
