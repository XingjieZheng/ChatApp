package com.xingjiezheng.chatapp.db;


import android.content.Context;

/**
 * Created by XingjieZheng
 * on 2016/9/6.
 */
public class DBManager {
    private final static String dbName = "chat_app_db";
    private static DBManager mInstance;
    private Context context;
    public static final boolean ENCRYPTED = true;


    private DBManager(Context context) {
        this.context = context;
    }

    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);

                }
            }
        }
        return mInstance;
    }
}
