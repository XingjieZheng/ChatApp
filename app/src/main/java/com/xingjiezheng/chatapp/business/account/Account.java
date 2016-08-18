package com.xingjiezheng.chatapp.business.account;

import com.xingjiezheng.chatapp.util.UserUtils;

import java.util.List;

import okhttp3.Cookie;

/**
 * Created by XingjieZheng
 * on 2016/5/19.
 */
public class Account {

    private static final String TAG = Account.class.getSimpleName();
    private final int userId;
    private String account;
    private String password;
    private boolean isSavePassword;
    private List<Cookie> cookieList;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUserIdValid() {
        return UserUtils.isUserIdValid(userId);
    }
}
