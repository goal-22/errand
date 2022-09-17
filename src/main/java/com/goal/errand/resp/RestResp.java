package com.goal.errand.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 11:13
 * @description：响应结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResp<T> {

    public Integer code;
    public String msg;
    public T result;

}
