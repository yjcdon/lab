package com.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 跨域配置
* */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOrigins("*") // 允许跨域的域名，可以用*表示允许任何域名使用
                .allowedMethods("*") // 允许任何请求类型
                .allowedHeaders("*")
                .exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L); // maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
    }
}