package com.goal.errand.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/10 20:54
 * @description：test
 */
@Api(value = "测试Api")
@RestController
public class TestController {

    @ApiOperation(value = "test")
    @GetMapping
    public String test(@RequestParam(required = true) Integer id){
        return "Test!!!";
    }

}
