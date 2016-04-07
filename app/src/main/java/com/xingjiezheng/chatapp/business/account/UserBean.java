package com.xingjiezheng.chatapp.business.account;

/**
 * Created by XingjieZheng
 * on 2016/4/7.
 */
public class UserBean {
    private int userId;


    private String userName;

    public UserBean() {

    }

    public UserBean(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
