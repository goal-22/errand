package com.goal.errand.controller;

import com.goal.errand.consts.AppConsts;
import com.goal.errand.resp.RestResp;
import com.goal.errand.service.LoginService;
import com.goal.errand.utils.UUIDUtil;
import com.goal.errand.vo.WeChatLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    private String uuid = UUIDUtil.getUUID();

    @ApiOperation(value = "微信登录api", notes = "处理微信登录逻辑")
    @GetMapping("/wechat-login")
    public RestResp<WeChatLoginVo> weChatLogin(@RequestParam("code") String code, HttpSession session) {
        RestResp<WeChatLoginVo> resp = loginService.weChatLogin(code);
        session.setAttribute(AppConsts.USER_OPEN_ID + uuid,resp.result.getOpenId());
        return resp;
    }

}
