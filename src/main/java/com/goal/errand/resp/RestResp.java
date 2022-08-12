package com.goal.errand.resp;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 11:13
 * @description：响应结果
 */
public class RestResp<T> {

    public Integer code;
    public String msg;
    public T result;

}
