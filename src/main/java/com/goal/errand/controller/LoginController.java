package com.goal.errand.controller;

import com.goal.errand.resp.RestResp;
import com.goal.errand.service.LoginService;
import com.goal.errand.vo.WeChatLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 11:12
 * @description：登录控制器
 */
@Api(value = "登录api")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "微信登录api", notes = "处理微信登录逻辑")
    @GetMapping("/wechat-login")
    public RestResp<WeChatLoginVo> weChatLogin(@RequestParam("code") String code) {
        System.out.println(code);
        return loginService.weChatLogin(code);
    }

}
