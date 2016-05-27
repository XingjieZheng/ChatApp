package com.xingjiezheng.chatapp.db.table;

/**
 * Created by XingjieZheng
 * on 2016/5/26.
 */
public interface AccountTable extends BaseTable {

    String TABLE_NAME = "account";

    String ACCOUNT_ID = "account_id";
    String ACCOUNT_PWD = "account_pwd";
    String ACCOUNT_USER_ID = "account_user_id";
    String ACCOUNT_COOKIE = "account_cookie";
    String ACCOUNT_LOGIN_TIME = "account_login_time";

    String SQL_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ACCOUNT_ID + " TEXT, "
                    + ACCOUNT_PWD + " TEXT, "
                    + ACCOUNT_USER_ID + " INTEGER, "
                    + ACCOUNT_COOKIE + " TEXT "
                    + ACCOUNT_LOGIN_TIME + " INTEGER)";

    String SQL_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
