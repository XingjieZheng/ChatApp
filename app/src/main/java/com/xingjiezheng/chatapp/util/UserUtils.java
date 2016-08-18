package com.xingjiezheng.chatapp.util;

/**
 * Created by XingjieZheng
 * on 2016/8/16.
 */
public class UserUtils {

    private UserUtils() {

    }

    public static boolean isUserIdValid(int userId) {
        return userId > 0;
    }

}
