package com.lab.interceptor;

import com.lab.dto.UserAuthDto;
import com.lab.exception.AuthException;
import com.lab.utils.UserUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) {
        UserAuthDto dto = UserUtil.get();
        // 不是导师，拦截指定请求
        if (dto.getIsTutor() == 0) {
            throw new AuthException("您没有权限执行功能!");
        }
        return true;
    }

}
