package com.goal.errand.utils;

import java.util.UUID;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 16:21
 * @description：生成UUID的工具类
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

}
