package com.lab.utils;

import com.lab.dto.UserAuthDto;

public class UserUtil {
    private static final ThreadLocal<UserAuthDto> threadLocal = new ThreadLocal<>();

    public static UserAuthDto get () {
        return threadLocal.get();
    }

    public static void remove () {
        threadLocal.remove();
    }

    public static void set (UserAuthDto userAuthDto) {
        threadLocal.set(userAuthDto);
    }
}
