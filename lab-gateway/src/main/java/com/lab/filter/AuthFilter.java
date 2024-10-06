package com.lab.filter;

import com.lab.dto.UserAuthDto;
import com.lab.exception.AuthException;
import com.lab.utils.UserUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component("authFilter")
public class AuthFilter implements GlobalFilter, Ordered {

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


    @Override
    public Mono<Void> filter (ServerWebExchange exchange, GatewayFilterChain chain) {
        UserAuthDto dto = UserUtil.get();

        // 如果是学生这些路径则拦截
        String path = exchange.getRequest().getURI().getPath();
        for (String p : AUTH_PATH) {
            if (path.equals(p) && dto.getIsTutor() == 0) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                throw new AuthException("无权限执行！");
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder () {
        return 1;
    }
}
