package com.xingjiezheng.chatapp.business.account;

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

    public void loginByAccountAndPassword(String account, String Password) {

    }
}
