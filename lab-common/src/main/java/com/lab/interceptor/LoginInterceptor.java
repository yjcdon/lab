package com.lab.interceptor;

import com.lab.utils.UserUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;

/*
 * 登录拦截器
 * */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("x-auth-token");
        if (token.isEmpty()) {

        }

        Integer userId = (Integer) redisTemplate.opsForValue().get(token);
        if (userId == null) {

        }

        // 把userId放入ThreadLocal中
        UserUtil.set(userId);
        return true;
    }

    public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除 ThreadLocal 中的userid，防止内存泄漏
        UserUtil.removeId();
    }
}
