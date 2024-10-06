package com.lab.interceptor;

import com.lab.dto.UserAuthDto;
import com.lab.exception.AuthException;
import com.lab.utils.UserUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 权限校验拦截器
* */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        UserAuthDto dto = UserUtil.get();
        // 不是导师，拦截指定请求
        if (dto.getIsTutor() == 0) {
            response.setStatus(403); // 无权限执行
            throw new AuthException("无权限执行！");
        }
        return true;
    }

}
