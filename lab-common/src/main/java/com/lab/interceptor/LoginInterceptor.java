package com.lab.interceptor;

import com.lab.constant.RedisConstant;
import com.lab.dto.UserAuthDto;
import com.lab.exception.LoginException;
import com.lab.utils.UserUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 登录拦截器
 * */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("x-auth-token");
        if (token.isEmpty()) {
            response.setStatus(401);
            throw new LoginException("未登录！");
        }

        UserAuthDto dto = (UserAuthDto) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN + token);
        if (dto == null) {
            response.setStatus(401);
            throw new LoginException("未登录！");
        }

        // 把userId放入ThreadLocal中
        UserUtil.set(dto);
        return true;
    }

    public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清除 ThreadLocal 中的userid，防止内存泄漏
        UserUtil.remove();
    }
}
