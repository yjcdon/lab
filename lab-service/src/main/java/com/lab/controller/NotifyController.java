package com.lab.controller;

import com.lab.dto.NotifyListDto;
import com.lab.response.Page;
import com.lab.response.Response;
import com.lab.service.NotifyService;
import com.lab.vo.NotifyListVo;
import com.lab.vo.NotifySingleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/notify")
@Api(tags = "通知相关接口")
public class NotifyController {
    @Resource
    private NotifyService notifyService;

    @ApiOperation("查询单个通知")
    @PostMapping("/getOne/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<NotifySingleVo> getById (@PathVariable Integer id) {
        return Response.success(notifyService.getById(id));
    }

    @ApiOperation("分页查询通知")
    @PostMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<Page<NotifyListVo>> list (@RequestBody NotifyListDto notifyListDto) {
        return Response.success(notifyService.list(notifyListDto));
    }

    @ApiOperation("删除通知")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> delete (@RequestParam("ids") List<Integer> ids) {
        Integer count = notifyService.delete(ids);
        return count != null ? Response.success("删除成功") : Response.error("删除失败");
    }

    @ApiOperation("查询未查看过的通知个数-如果不为0，则弹出气泡作为任务提醒")
    @GetMapping("/getIsNotLookCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<Integer> getIsNotLookCount(){
        return Response.success(notifyService.getIsNotLookCount());
    }

}
