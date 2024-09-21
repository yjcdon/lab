package com.lab.controller;

import com.lab.dto.UserListDto;
import com.lab.dto.UserLoginDto;
import com.lab.dto.UserRegisterDto;
import com.lab.dto.UserUpdateDto;
import com.lab.response.Page;
import com.lab.response.Response;
import com.lab.service.UserService;
import com.lab.vo.UserListVo;
import com.lab.vo.UserSingleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {
    @Resource
    private UserService userService;

    /*
     * 登录
     * */
    @ApiOperation("登录")
    @PostMapping("/login")
    public Response<String> login (@RequestBody UserLoginDto userLoginDto) {
        return Response.success(userService.login(userLoginDto));
    }

    /*
     * 注册
     * */
    @ApiOperation("注册")
    @PostMapping("/register")
    public Response<String> register (@RequestBody UserRegisterDto userRegisterDto) {
        return Response.success(userService.register(userRegisterDto));
    }

    /*
     * 查询当前用户信息，根据Id
     * */
    @ApiOperation("根据Id查询当前用户信息")
    @GetMapping("/getOne/{id}")
    public Response<UserSingleVo> getById (@PathVariable Integer id) {
        return Response.success(userService.getById(id));
    }

    /*
     * 分页查询用户，只有导师能做
     * */
    @ApiOperation("分页查询用户信息")
    @PostMapping("/list")
    public Response<Page<UserListVo>> list (@RequestBody UserListDto userListDto) {
        return Response.success(userService.list(userListDto));
    }

    /*
     * 修改用户信息
     * */
    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public Response<String> update (@RequestBody UserUpdateDto userUpdateDto) {
        return Response.success(userService.update(userUpdateDto));
    }

    /*
     * 删除用户
     * */
    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public Response<String> delete (@RequestParam("ids") List<Integer> ids) {
        return Response.success(userService.delete(ids));
    }

}
