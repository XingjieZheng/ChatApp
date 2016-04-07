package com.xingjiezheng.chatapp.business.account;

/**
 * Created by XingjieZheng
 * on 2016/4/7.
 */
public class UserModel {
    private UserBean userBean;

    public UserModel(int userId) {
        userBean = new UserBean(userId);
    }

    public int getUserId() {
        return userBean.getUserId();
    }

    public String getUserName() {
        return userBean.getUserName();
    }

    public UserBean load(int userId) {
        return new UserBean(userId);
    }


}
