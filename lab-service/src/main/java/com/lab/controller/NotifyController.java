package com.lab.controller;

import com.lab.dto.NotifyListDto;
import com.lab.response.Page;
import com.lab.response.Response;
import com.lab.service.NotifyService;
import com.lab.vo.NotifyListVo;
import com.lab.vo.NotifySingleVo;
import io.swagger.annotations.Api;
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
    public Response<NotifySingleVo> getById (@PathVariable Integer id) {
        return Response.success(notifyService.getById(id));
    }

    @ApiOperation("分页查询通知")
    @PostMapping("/list")
    public Response<Page<NotifyListVo>> list (@RequestBody NotifyListDto notifyListDto) {
        return Response.success(notifyService.list(notifyListDto));
    }

    @ApiOperation("删除通知")
    @PostMapping("/delete")
    public Response<String> delete (@RequestParam("ids") List<Integer> ids) {
        Integer count = notifyService.delete(ids);
        return count != null ? Response.success("删除成功") : Response.error("删除失败");
    }


}
