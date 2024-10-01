package com.lab.exception;

/*
* 验证身份异常
* */
public class AuthException extends RuntimeException{
    public AuthException () {
    }

    public AuthException (String message) {
        super(message);
    }
}
