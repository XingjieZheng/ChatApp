package com.xingjiezheng.chatapp.business.account.login;

import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.framework.BaseBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录的初始信息
 */
public class AccountLoginBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    private String account;
    /**
     * 登录信息-本地账户密码
     */
    private String accountPwd;
    /**
     * 登录信息-是否记录密码
     */
    private boolean isRememberPassword;
    private User user;

    /**
     * cookie
     */
    private HashMap<String, String> cookieMap;

    public HashMap<String, String> getCookieMap() {
        return cookieMap;
    }

    public String getCookieMapInString() {
        if (cookieMap == null || cookieMap.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> cookie : cookieMap.entrySet()) {
            sb.append(cookie.getKey()).append("=")
                    .append(cookie.getValue()).append(";");
        }
        return sb.toString();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public boolean isRememberPassword() {
        return isRememberPassword;
    }

    public void setRememberPassword(boolean rememberPassword) {
        isRememberPassword = rememberPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCookieMap(HashMap<String, String> cookieMap) {
        this.cookieMap = cookieMap;
    }

}
