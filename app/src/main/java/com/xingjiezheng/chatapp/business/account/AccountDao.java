package com.xingjiezheng.chatapp.business.account;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.xingjiezheng.chatapp.db.DbManager;
import com.xingjiezheng.chatapp.db.table.AccountTable;

/**
 * Created by XingjieZheng
 * on 2016/5/26.
 */
public class AccountDao implements AccountTable {

    private static AccountDao accountDao;
    private SQLiteDatabase db;

    private AccountDao() {
        db = DbManager.getInstance().getSqLiteDatabase();
    }

    public static AccountDao getInstance() {
        if (accountDao == null) {
            synchronized (AccountDao.class) {
                if (accountDao == null) {
                    accountDao = new AccountDao();
                }
            }
        }
        return accountDao;
    }

    public void saveLoginAccount(Account account) {
        ContentValues contentValues = new ContentValues();



    }
}
