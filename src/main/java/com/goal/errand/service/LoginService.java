package com.goal.errand.service;

import com.goal.errand.resp.RestResp;
import com.goal.errand.vo.WeChatLoginVo;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 12:26
 * @description：登录服务
 */
public interface LoginService {

    /**
     * 微信登录
     * @param code
     * @return
     */
    public RestResp<String> weChatLogin(String code);

}
