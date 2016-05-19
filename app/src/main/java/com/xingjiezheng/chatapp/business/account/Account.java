package com.xingjiezheng.chatapp.business.account;

/**
 * Created by XingjieZheng
 * on 2016/5/19.
 */
public class Account {

    private static final String TAG = "Account";
    private final int userId;
    private String cookie;
    private String account;
    private String password;
    private boolean isSavePassword;

    public Account(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
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
}
