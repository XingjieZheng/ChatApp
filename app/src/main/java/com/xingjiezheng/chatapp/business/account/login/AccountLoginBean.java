package com.xingjiezheng.chatapp.business.account.login;

import com.xingjiezheng.chatapp.framework.BaseBean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 登录的初始信息
 */
public class AccountLoginBean extends BaseBean implements Serializable {
    private Data data;

    /**
     * 用户id
     */
    private String accountName;
    /**
     * 登录信息-是否记录密码
     */
    private boolean isRememberPassword;

    /**
     * 登录信息-本地账户密码
     */
    private String accountPwd;


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean isRememberPassword() {
        return isRememberPassword;
    }

    public void setRememberPassword(boolean isRememberPassword) {
        this.isRememberPassword = isRememberPassword;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public static class Data implements Serializable {
        private static final long serialVersionUID = 1L;


        /**
         * 用户性别
         */
        private int gender;
        /**
         * cookie
         */
        private HashMap<String, String> cookieMap;
        /**
         * 用户唯一标示
         */
        private String userId;


        private boolean isLogin = false;

        private String nickName;

        private String avatar;
        private String head;
        /**
         * 1 用户不存在；2 密码错误
         */
        private int state;

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public HashMap<String, String> getCookieMap() {
            return cookieMap;
        }

        public void setCookieMap(HashMap<String, String> cookieMap) {
            this.cookieMap = cookieMap;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

}
