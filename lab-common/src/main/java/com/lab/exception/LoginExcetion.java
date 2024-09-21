package com.lab.exception;

public class LoginExcetion extends RuntimeException{
    public LoginExcetion () {
    }

    public LoginExcetion (String message) {
        super(message);
    }
}
