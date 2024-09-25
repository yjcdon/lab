package com.lab.mapper;

import com.lab.dto.UserListDto;
import com.lab.dto.UserLoginDto;
import com.lab.dto.UserRegisterDto;
import com.lab.dto.UserUpdateDto;
import com.lab.vo.UserListVo;
import com.lab.vo.UserSingleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: 梁雨佳
 * Date: 2024/9/19 21:42:23
 * Description:
 */
public interface UserMapper {
    boolean getByNameAndPsw (@Param("dto") UserLoginDto userLoginDto);

    boolean register (@Param("dto") UserRegisterDto userRegisterDto);

    UserSingleVo getById (@Param("id") Integer id);

    List<UserListVo> list (@Param("dto") UserListDto userListDto);

    boolean update (@Param("dto") UserUpdateDto userUpdateDto);

    boolean deleteByIds (@Param("ids") List<Integer> ids);
}
