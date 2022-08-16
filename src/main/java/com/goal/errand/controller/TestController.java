package com.goal.errand.controller;

import com.goal.errand.annotation.PermissionAnn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/10 20:54
 * @description：test
 */
@Api(value = "测试Api")
@RestController
public class TestController {

    @ApiOperation(value = "test")
    @GetMapping("/")
    public String test(String path){
        ApplicationHome applicationHome = new ApplicationHome(getClass());
//        D:\Goal\Document\errand\target\classes
        File homeSource = applicationHome.getSource();
//        在jar包目录下生成一个文件上传目录
        String uploadPath = homeSource.getParent() + "\\upload";
        return "Test!!!";
    }

}
