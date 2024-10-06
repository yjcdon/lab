package com.lab.filter;

import com.lab.constant.RedisConstant;
import com.lab.dto.UserAuthDto;
import com.lab.exception.LoginException;
import com.lab.utils.UserUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component("loginFilter")
public class LoginFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 未登录可以访问的路径
    private static final String[] LOGIN_PATH = {
            "/user/login",
            "/user/register"
    };

    @Override
    public Mono<Void> filter (ServerWebExchange exchange, GatewayFilterChain chain) {
        // 排除不需要登录的路径
        String path = exchange.getRequest().getURI().getPath();
        for (String p : LOGIN_PATH) {
            if(path.equals(p)){
                // 放行
                return chain.filter(exchange);
            }
        }

        String token = exchange.getRequest().getHeaders().getFirst("x-auth-token");
        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            throw new LoginException("未登录！");
        }

        UserAuthDto dto = (UserAuthDto) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN + token);
        if (dto == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            throw new LoginException("未登录！");
        }

        // 把userId放入ThreadLocal中
        UserUtil.set(dto);

        return chain.filter(exchange)
                // 验证登录结束后，删除user
                .then(Mono.fromRunnable(UserUtil::remove));
    }

    @Override
    public int getOrder () {
        return -1;
    }
}