package com.xingjiezheng.chatapp.business.account;

import com.xingjiezheng.chatapp.business.cookie.CookieManager;
import com.xingjiezheng.chatapp.business.Global;
import com.xingjiezheng.chatapp.util.UserUtils;

import java.util.List;

import okhttp3.Cookie;

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
            Global.loginAccount = account;
        }
        return account;
    }

    public Account getLoginAccountFromSql() {
        return AccountDao.getInstance().getLoginAccount();
    }

    private void saveLoginAccountInSql(Account account) {
        AccountDao.getInstance().saveLoginAccount(account);

    }

    public void saveLoginAccountInfo(Account account) {
        if (account == null || !account.isUserIdValid()) {
            return;
        }
        List<Cookie> cookieArrayList = null;
        if (Global.loginAccount != null
                && Global.loginAccount.isUserIdValid()
                && Global.loginAccount.getUserId() == account.getUserId()
                && Global.loginAccount.getCookieList() != null
                && Global.loginAccount.getCookieList().size() > 0) {
            cookieArrayList = Global.loginAccount.getCookieList();
        }
        if (cookieArrayList != null) {
            account.setCookieList(cookieArrayList);
        }
        Global.loginAccount = account;
        saveLoginAccountInSql(account);
    }

    public void saveLoginAccountCookieList(int userId, String token, List<Cookie> cookieList) {
        if (!UserUtils.isUserIdValid(userId) || token == null || cookieList == null || cookieList.size() == 0) {
            return;
        }
        if (Global.loginAccount != null
                && Global.loginAccount.isUserIdValid()
                && Global.loginAccount.getUserId() == userId) {
            if (!token.equals(CookieManager.getInstance().getCookieToken(Global.loginAccount.getCookieList()))) {
                //same account but not same cookie
                Global.loginAccount.setCookieList(cookieList);
                AccountDao.getInstance().saveLoginAccountCookie(userId, cookieList);
            }
        } else {
            //different account
            Global.loginAccount = new Account(userId);
            Global.loginAccount.setCookieList(cookieList);
            AccountDao.getInstance().saveLoginAccountCookie(userId, cookieList);
        }
    }
}
