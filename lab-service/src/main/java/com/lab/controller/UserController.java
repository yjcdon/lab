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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        String uuid = userService.login(userLoginDto);
        return !uuid.isEmpty() ? Response.success(uuid) : Response.error("登录失败");
    }

    /*
     * 退出登录
     * */
    @ApiOperation("退出登录")
    @GetMapping("/logout")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> logout (HttpServletRequest request) {
        return Response.success(userService.logout(request));
    }

    /*
     * 注册
     * */
    @ApiOperation("注册")
    @PostMapping("/register")
    public Response<String> register (@RequestBody UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto) ? Response.success("注册成功") : Response.error("注册失败");
    }

    /*
     * 查询当前用户信息，根据Id
     * */
    @ApiOperation("根据Id查询当前用户信息")
    @GetMapping("/getOne/{id}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<UserSingleVo> getById (@PathVariable Integer id) {
        return Response.success(userService.getById(id));
    }

    /*
     * 分页查询用户，只有导师能做
     * */
    @ApiOperation("分页查询用户信息")
    @PostMapping("/list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<Page<UserListVo>> list (@RequestBody UserListDto userListDto) {
        return Response.success(userService.list(userListDto));
    }

    /*
     * 修改用户信息
     * */
    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> update (@RequestBody UserUpdateDto userUpdateDto) {
        return userService.update(userUpdateDto) ? Response.success("更新成功") : Response.error("更新失败");
    }

    /*
     * 删除用户
     * */
    @ApiOperation("删除用户")
    @PostMapping("/delete")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> delete (@RequestParam("ids") List<Integer> ids) {
        return userService.delete(ids) ? Response.success("删除成功") : Response.error("删除失败");
    }

}
