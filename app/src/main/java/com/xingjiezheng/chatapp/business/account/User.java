package com.xingjiezheng.chatapp.business.account;

import java.io.Serializable;

/**
 * Created by XingjieZheng
 * on 2016/4/7.
 */
public class User implements Serializable {
    private static final int GENDER_MALE = 1;
    private static final int GENDER_FEMALE = 2;

    private String userId;

    private String userName;

    private int gender;

    private String avatar;

    public User() {

    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isMale() {
        return GENDER_MALE == gender;
    }

    public boolean isFemale() {
        return GENDER_FEMALE == gender;
    }
}
