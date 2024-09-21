package com.lab.config;

import com.lab.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Resource
    private LoginInterceptor loginInterceptor;

    protected void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)//添加自定义拦截器
                .addPathPatterns("/**")//拦截什么请求路径
                .excludePathPatterns("/user/login");//不拦截的路径

    }
}
