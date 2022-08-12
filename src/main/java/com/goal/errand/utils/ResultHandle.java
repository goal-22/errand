package com.goal.errand.utils;

import com.goal.errand.resp.RestResp;
import org.springframework.stereotype.Component;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 12:45
 * @description：结果处理
 */
@Component
public class ResultHandle<T> {

    public RestResp<T> resultHandle(Integer code, String msg, T result){
        RestResp<T> resp = new RestResp<>();
        resp.code = code;
        resp.msg = msg;
        resp.result = result;
        return resp;
    }


}
