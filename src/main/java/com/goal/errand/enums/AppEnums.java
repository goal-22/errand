package com.goal.errand.enums;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/10 21:36
 * @description：常量定义
 */
public enum  AppEnums {

    //  通用模块
    SUCCESS(1001,"请求成功"),
    FAIL(1002,"请求失败");

    private Integer code;
    private String msg;

    private AppEnums(){}
    private AppEnums(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


}
