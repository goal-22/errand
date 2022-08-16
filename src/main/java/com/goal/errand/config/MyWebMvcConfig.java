package com.goal.errand.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/15 15:52
 * @description：上传文件路径配置
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ApplicationHome applicationHome = new ApplicationHome(getClass());
//        D:\Goal\Document\errand\target\classes
        File homeSource = applicationHome.getSource();
//        在jar包目录下生成一个文件上传目录
        String uploadPath = homeSource.getParent() + "/upload/";
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + uploadPath);
    }
}
