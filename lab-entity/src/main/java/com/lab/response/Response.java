package com.lab.response;

import lombok.Data;

@Data
public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Response<T> success () {
        Response<T> Response = new Response<T>();
        Response.msg = "成功";
        Response.code = 200;
        return Response;
    }

    public static <T> Response<T> success (T object) {
        Response<T> Response = new Response<T>();
        Response.data = object;
        Response.msg = "成功";
        Response.code = 200;
        return Response;
    }

    public static <T> Response<T> error (String msg) {
        Response Response = new Response();
        Response.msg = msg;
        Response.code = 0;
        return Response;
    }

    /*
    * 发生异常时使用，code是状态码，msg是错误表示的信息，data是展示给用户的信息
    * */
    public static <T> Response<T> error (Integer code, String msg, T data) {
        Response Response = new Response();
        Response.msg = msg;
        Response.code = code;
        Response.data = data;
        return Response;
    }
}
