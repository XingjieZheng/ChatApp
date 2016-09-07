package com.xingjiezheng.chatapp.framework;

import android.app.Application;

import com.xingjiezheng.chatapp.business.Global;
import com.xingjiezheng.chatapp.db.DbManager;

/**
 * Created by XingjieZheng
 * on 2016/5/19.
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Global.getInstance(getApplicationContext());
        DbManager.getInstance(getApplicationContext());
    }
}
