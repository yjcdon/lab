package com.lab.config;

import com.lab.interceptor.AuthInterceptor;
import com.lab.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Resource
    private LoginInterceptor loginInterceptor;
    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor) // 注册自定义拦截器
                .addPathPatterns("/user/**") // 拦截的路径
                .excludePathPatterns("/user/login"); // 排除的路径

        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/user/list", "/user/delete");

    }

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
