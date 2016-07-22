package com.xingjiezheng.chatapp.business;

import android.content.Context;

import com.xingjiezheng.chatapp.business.account.Account;
import com.xingjiezheng.chatapp.db.DbManager;

/**
 * Created by XingjieZheng
 * on 2016/5/19.
 */
public class Global {


    private static Global instance;
    public static Context appContext;
    public static Account loginAccount;

    private Global(Context appContext) {
        Global.appContext = appContext;
        DbManager.getInstance();
    }

    public static Global getInstance(Context appContext) {
        if (instance == null) {
            synchronized (Global.class) {
                if (instance == null) {
                    instance = new Global(appContext);
                }
            }
        }
        return instance;
    }

}
