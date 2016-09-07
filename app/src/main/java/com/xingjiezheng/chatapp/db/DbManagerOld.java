package com.xingjiezheng.chatapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.xingjiezheng.chatapp.business.Global;
import com.xingjiezheng.chatapp.util.LogUtils;

/**
 * Created by XingjieZheng
 * on 2016/5/26.
 */
public class DbManagerOld {

    private static final String TAG = DbManagerOld.class.getSimpleName();


    private static DbManagerOld dbManager;
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;

    private DbManagerOld(Context context) {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            return;
        }
        try {
            if (dbHelper == null) {
                dbHelper = new DbHelper(context);
            }
            sqLiteDatabase = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            LogUtils.LOGE(TAG, e.getStackTrace().toString());
        }

    }

    public synchronized static DbManagerOld getInstance() {
        if (dbManager == null) {
            synchronized (DbManagerOld.class) {
                if (dbManager == null) {
                    dbManager = new DbManagerOld(Global.appContext);
                }
            }
        }
        return dbManager;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }
}
