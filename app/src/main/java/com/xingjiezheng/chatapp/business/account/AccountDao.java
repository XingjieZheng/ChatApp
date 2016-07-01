package com.xingjiezheng.chatapp.business.account;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xingjiezheng.chatapp.api.CookieManager;
import com.xingjiezheng.chatapp.db.DbManager;
import com.xingjiezheng.chatapp.db.table.AccountTable;

import java.util.List;

import okhttp3.Cookie;

/**
 * Created by XingjieZheng
 * on 2016/5/26.
 */
public class AccountDao implements AccountTable {


    private static final String TAG = AccountDao.class.getSimpleName();
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
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, new String[]{_ID}, ACCOUNT_ID + "=?", new String[]{account.getAccount()}, null, null, null);
            int id = -1;
            if (cursor.moveToNext() && cursor.getCount() >= 0) {
                id = cursor.getInt(cursor.getColumnIndex(_ID));
            }
            if (id != -1) {
                updateAccount(account, id);
            } else {
                addAccount(account);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private void addAccount(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_ID, account.getAccount());
        contentValues.put(ACCOUNT_PWD, account.getPassword());
        contentValues.put(ACCOUNT_USER_ID, account.getUserId());
        contentValues.put(ACCOUNT_COOKIE, CookieManager.getInstance().getCookieListJson(account.getCookieList()));
        contentValues.put(ACCOUNT_LOGIN_TIME, (int) (System.currentTimeMillis() / 1000));
        db.insert(TABLE_NAME, null, contentValues);
    }

    private void updateAccount(Account account, int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_ID, account.getAccount());
        contentValues.put(ACCOUNT_PWD, account.getPassword());
        contentValues.put(ACCOUNT_USER_ID, account.getUserId());
        contentValues.put(ACCOUNT_COOKIE, CookieManager.getInstance().getCookieListJson(account.getCookieList()));
        contentValues.put(ACCOUNT_LOGIN_TIME, (int) (System.currentTimeMillis() / 1000));
        db.update(TABLE_NAME, contentValues, _ID + "= ?", new String[]{String.valueOf(id)});
    }

    public Account getLoginAccount() {
        Account account = null;
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, ACCOUNT_LOGIN_TIME, "1");

            if (cursor.moveToNext() && cursor.getCount() >= 0) {
                account = new Account(cursor.getString(cursor.getColumnIndex(ACCOUNT_USER_ID)));
                account.setAccount(cursor.getString(cursor.getColumnIndex(ACCOUNT_ID)));
                account.setPassword(cursor.getString(cursor.getColumnIndex(ACCOUNT_PWD)));
                String cookieListJson = cursor.getString(cursor.getColumnIndex(ACCOUNT_COOKIE));
                if (cookieListJson != null && !"".equals(cookieListJson)) {
                    List<Cookie> cookieList = CookieManager.getInstance().getCookieListFromJson(cookieListJson);
                    if (cookieList != null && cookieList.size() > 0) {
                        account.setCookieList(cookieList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cursor != null) {
            cursor.close();
        }
        return account;
    }

    public void saveLoginAccountCookie(String userId, List<Cookie> cookieList) {
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, new String[]{_ID}, ACCOUNT_USER_ID + "=?", new String[]{userId}, null, null, null);
            int id = -1;
            if (cursor.moveToNext() && cursor.getCount() >= 0) {
                id = cursor.getInt(cursor.getColumnIndex(_ID));
            }
            if (id != -1) {
                updateAccountCookieList(cookieList, id);
            } else {
                addAccountCookieList(cookieList, userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private void addAccountCookieList(List<Cookie> cookieList, String userId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_ID, "");
        contentValues.put(ACCOUNT_PWD, "");
        contentValues.put(ACCOUNT_USER_ID, userId);
        contentValues.put(ACCOUNT_COOKIE, CookieManager.getInstance().getCookieListJson(cookieList));
        contentValues.put(ACCOUNT_LOGIN_TIME, (int) (System.currentTimeMillis() / 1000));
        db.insert(TABLE_NAME, null, contentValues);
    }

    private void updateAccountCookieList(List<Cookie> cookieList, int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_COOKIE, CookieManager.getInstance().getCookieListJson(cookieList));
        db.update(TABLE_NAME, contentValues, _ID + "= ?", new String[]{String.valueOf(id)});
    }

}
