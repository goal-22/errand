package com.goal.errand.controller;

import com.goal.errand.annotation.Auth;
import com.goal.errand.resp.RestResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/")
    @Auth
    public RestResp<String> test(){
        return new RestResp<String>(1111,"3sdf",null);
    }

}
