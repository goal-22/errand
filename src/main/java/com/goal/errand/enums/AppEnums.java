package com.goal.errand.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/10 21:36
 * @description：返回结果码定义
 */
@Getter
public enum  AppEnums {

    //  通用模块
    SUCCESS(1001,"请求成功"),
    FAIL(1002,"请求失败"),
    //  登录模块
    LOGIN_SUCCESS(2001,"登录成功"),
    LOGIN_FAIL(2002,"登录失败"),
    PLEASE_LOGIN(2003,"您还未登录,请先登录"),
    //  文件模块
    UPLOAD_SUCCESS(3001,"上传成功"),
    UPLOAD_FAIL(3002,"上传失败"),
    FILE_ERROR(3003,"上传文件有误")
    ;

    private Integer code;
    private String msg;

    private AppEnums(){}
    private AppEnums(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


}
