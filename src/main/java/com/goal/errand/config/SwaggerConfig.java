package com.goal.errand.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ：Goal
 * @date ：Created in 2022/8/10 19:44
 * @description：Swagger配置类
 */
@Configuration
@EnableSwagger2     //开启Swagger功能
public class SwaggerConfig {

    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                // 用于生成API信息
                .apiInfo(apiInfo())
                // select()函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
                .select()
                // 选择所有的API,如果你想只为部分API生成文档，可以配置这里
                .paths(PathSelectors.any())
                // 用于指定扫描哪个包下的接口
                .apis(RequestHandlerSelectors.basePackage("com.goal.errand.controller"))
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //  可以用来自定义API的主标题
                .title("客户端api")
                // 可以用来描述整体的API
                .description("校园跑腿小程序api")
                // 可以用来定义版本。
                .version("v1.0.0")
                // 用于定义服务的域名
                .termsOfServiceUrl("")
                .build();
    }

}
