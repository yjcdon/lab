package com.lab.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskTag")
@Api(tags = "任务标签相关接口")
public class TaskTagController {
}
