package com.xingjiezheng.chatapp.framework;

import com.google.gson.Gson;

/**
 * Created by XingjieZheng
 * on 2016/8/16.
 */
public class BaseToStringInstance {

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
