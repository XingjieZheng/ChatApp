package com.xingjiezheng.chatapp.db;


import android.content.Context;

/**
 * Created by XingjieZheng
 * on 2016/9/6.
 */
public class DbManager {
    private final static String dbName = "chat_app_db";
    private static DbManager mInstance;
    private Context context;
    public static final boolean ENCRYPTED = true;


    private DbManager(Context context) {
        this.context = context;
    }

    public static DbManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DbManager.class) {
                if (mInstance == null) {
                    mInstance = new DbManager(context);

                }
            }
        }
        return mInstance;
    }
}
