package com.lab.controller;

import com.lab.dto.TaskAddDto;
import com.lab.dto.TaskListDto;
import com.lab.dto.TaskUpdateDto;
import com.lab.response.Page;
import com.lab.response.Response;
import com.lab.service.TaskService;
import com.lab.vo.TaskListVo;
import com.lab.vo.TaskSingleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/task")
@Api(tags = "任务相关接口")
public class TaskController {
    @Resource
    private TaskService taskService;

    @ApiOperation("新增任务")
    @PostMapping("/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> add (@RequestBody TaskAddDto taskAddDto) {
        Integer taskId = taskService.add(taskAddDto);
        return taskId != null ? Response.success("添加成功") : Response.error("添加失败");
    }

    @ApiOperation("删除任务")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> delete (@RequestParam("ids") List<Integer> ids) {
        Integer count = taskService.delete(ids);
        return count > 0 ? Response.success("成功删除 " + count + " 个任务") : Response.error("删除失败");
    }

    @ApiOperation("修改任务")
    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<String> update (@RequestBody TaskUpdateDto taskUpdateDto) {
        return taskService.update(taskUpdateDto) ? Response.success("更新成功") : Response.error("更新失败");
    }

    @ApiOperation("查询单个任务具体信息")
    @GetMapping("/getOne/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<TaskSingleVo> getById (@PathVariable Integer id) {
        return Response.success(taskService.getById(id));
    }

    @ApiOperation("分页查询任务")
    @PostMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    })
    public Response<Page<TaskListVo>> list (@RequestBody TaskListDto taskListDto) {
        return Response.success(taskService.list(taskListDto));
    }

    // @ApiOperation("任务通知")
    // @PostMapping("/notify")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    // })
    // public Response<>
    //
    // @ApiOperation("任务分配")
    // @PostMapping("/assign")
    // @ApiImplicitParams({
    //     @ApiImplicitParam(name = "x-auth-token", value = "登录token", required = true, dataType = "string", paramType = "header")
    // })
    // public Response<>

}
