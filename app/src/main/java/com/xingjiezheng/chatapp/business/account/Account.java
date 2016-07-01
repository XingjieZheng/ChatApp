package com.xingjiezheng.chatapp.business.account;

import java.util.List;

import okhttp3.Cookie;

/**
 * Created by XingjieZheng
 * on 2016/5/19.
 */
public class Account {

    private static final String TAG = Account.class.getSimpleName();
    private final String userId;
    private String account;
    private String password;
    private boolean isSavePassword;
    private List<Cookie> cookieList;

    public Account(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isSavePassword() {
        return isSavePassword;
    }

    public void setSavePassword(boolean savePassword) {
        isSavePassword = savePassword;
    }

    public List<Cookie> getCookieList() {
        return cookieList;
    }

    public void setCookieList(List<Cookie> cookieList) {
        this.cookieList = cookieList;
    }
}
