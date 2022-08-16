package com.goal.errand.annotation;

import java.lang.annotation.*;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/12 16:26
 * @description：权限注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAnn {
}
