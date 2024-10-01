package com.lab.handler;

import com.lab.exception.AuthException;
import com.lab.exception.BusinessException;
import com.lab.commonEnum.ExceptionEnum;
import com.lab.exception.LoginException;
import com.lab.response.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
    * 发生没指定的异常类型，默认处理方法
    * */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<String> defaultExceptionHandler (Exception e) {
        log.error("发生未知异常，原因：{}", e.getStackTrace());
        return Response.error(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode(), ExceptionEnum.INTERNAL_SERVER_ERROR.getMessage(), "服务器开小差了，请联系管理员。");
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Response<String> runtimeExceptionHandler (NullPointerException e) {
        log.error("发生空指针异常，原因：{}", e.getStackTrace());
        return Response.error(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode(), ExceptionEnum.INTERNAL_SERVER_ERROR.getMessage(), e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Response<String> businessExceptionHandler (BusinessException e) {
        log.error("发生业务异常，原因：{}", e.getStackTrace());
        return Response.error(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode(), ExceptionEnum.INTERNAL_SERVER_ERROR.getMessage(), e.getMessage());
    }

    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Response<String> loginExceptionHandler (LoginException e) {
        log.error("发生登录异常，原因：{}", e.getStackTrace());
        return Response.error(ExceptionEnum.UNAUTHORIZED.getCode(), ExceptionEnum.UNAUTHORIZED.getMessage(), e.getMessage());
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public Response<String> authExceptionHandler (AuthException e) {
        log.error("发生身份验证异常，原因：{}", e.getStackTrace());
        return Response.error(ExceptionEnum.FORBIDDEN.getCode(), ExceptionEnum.FORBIDDEN.getMessage(), e.getMessage());
    }

}
