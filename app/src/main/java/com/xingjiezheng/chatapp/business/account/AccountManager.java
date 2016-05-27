package com.xingjiezheng.chatapp.business.account;

import com.xingjiezheng.chatapp.business.Global;

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
//        if (account == null) {
            account = getLoginAccountFromSql();
//        }
        return account;
    }

    public Account getLoginAccountFromSql() {
        return AccountDao.getInstance().getLoginAccount();
    }

    private void saveLoginAccountInSql(Account account) {
        AccountDao.getInstance().saveLoginAccount(account);

    }

    public void saveLoginAccount(Account account) {
        Global.loginAccount = account;
        saveLoginAccountInSql(account);
    }

}
