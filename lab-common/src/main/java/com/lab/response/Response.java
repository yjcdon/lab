package com.lab.response;

import lombok.Data;

@Data
public class Response<T> {
    private Integer code; // 1成功，0失败
    private String msg;
    private T data;

    public static <T> Response<T> success () {
        Response<T> Response = new Response<T>();
        Response.code = 1;
        return Response;
    }

    public static <T> Response<T> success (T object) {
        Response<T> Response = new Response<T>();
        Response.data = object;
        Response.code = 1;
        return Response;
    }

    public static <T> Response<T> error (String msg) {
        Response Response = new Response();
        Response.msg = msg;
        Response.code = 0;
        return Response;
    }
}
