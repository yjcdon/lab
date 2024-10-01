package com.lab.controller;

import com.lab.response.Page;
import com.lab.response.Response;
import com.lab.service.TaskTypeService;
import com.lab.vo.NameAndCodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/taskType")
@Api(tags = "任务类型相关接口")
public class TaskTypeController {
    @Resource
    private TaskTypeService taskTypeService;

    @ApiOperation("新增任务类型")
    @PostMapping("/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> add (String name) {
        Integer count = taskTypeService.add(name);
        return count != null ? Response.success("添加成功") : Response.error("添加失败");
    }

    @ApiOperation("删除任务类型")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> delete (@RequestParam("ids") List<Integer> ids) {
        Integer count = taskTypeService.delete(ids);
        return count != null ? Response.success("删除成功") : Response.error("删除失败");
    }

    @ApiOperation("修改任务类型")
    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> update (Integer id, String name) {
        return taskTypeService.update(id, name) ? Response.success("更新成功") : Response.error("更新失败");
    }

    @ApiOperation("分页查询任务类型")
    @PostMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<Page<NameAndCodeVo>> list (int pageNum, int pageSize) {
        return Response.success(taskTypeService.list(pageNum, pageSize));
    }
}