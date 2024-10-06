package com.lab.config;

import com.lab.interceptor.AuthInterceptor;
import com.lab.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Resource
    private LoginInterceptor loginInterceptor;
    @Resource
    private AuthInterceptor authInterceptor;

    // 未登录可以访问的路径
    private static final String[] LOGIN_PATH = {
            "/user/login",
            "/user/register"
    };

    // 学生无法访问的路径
    private static final String[] AUTH_PATH = {
            "/user/list",
            "/user/delete",

            "/task/add",
            "/task/delete",
            "/task/update",
            "/task/notify",

            "/taskTag/add",
            "/taskTag/delete",
            "/taskTag/update",

            "/taskType/add",
            "/taskType/delete",
            "/taskType/update"
    };

    // 拦截的路径
    private static final String[] INTERCEPT_PATH = {
            "/user/**",
            "/task/**",
            "/notify/**",
            "/taskTag/**",
            "/taskType/**"
    };


    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(loginInterceptor) // 注册自定义拦截器
                .addPathPatterns(INTERCEPT_PATH) // 拦截的路径
                .excludePathPatterns(LOGIN_PATH); // 排除的路径

        // 权限控制拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(AUTH_PATH);

    }

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
