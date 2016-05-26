package com.xingjiezheng.chatapp.business.account;

import android.database.sqlite.SQLiteDatabase;

import com.xingjiezheng.chatapp.business.Global;
import com.xingjiezheng.chatapp.db.DbManager;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public class AccountManager {

    private static AccountManager accountManager;

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (accountManager == null) {
            synchronized (AccountManager.class) {
                if (accountManager == null) {
                    accountManager = new AccountManager();
                }
            }
        }
        return accountManager;
    }

    public Account getLoginAccount() {
        Account account = Global.loginAccount;
        if (account == null) {
            account = getLoginAccountFromSql();
        }
        return account;
    }

    private Account getLoginAccountFromSql() {
        // TODO: 2016/5/19
        return new Account(123456);
    }

    private void saveLoginAccountInSql() {

    }

    public void saveLoginAccount(Account account) {
        Global.loginAccount = account;
        saveLoginAccountInSql();
    }

}
