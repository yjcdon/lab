package com.lab.utils;

import com.lab.entity.User;

public class UserUtil {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /*
     * threadlocal 设置userid
     * */
    public static Integer getId () {
        return threadLocal.get();
    }

    public static void removeId () {
        threadLocal.remove();
    }

    public static void set (Integer userId) {
        threadLocal.set(userId);
    }
}
