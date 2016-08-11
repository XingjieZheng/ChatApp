package com.xingjiezheng.chatapp.business.account;

import java.io.Serializable;

/**
 * Created by XingjieZheng
 * on 2016/4/7.
 */
public class User implements Serializable {
    private static final int GENDER_MALE = 1;
    private static final int GENDER_FEMALE = 2;

    private String id;

    private String nickName;

    private int gender;

    private String avatar;

    public User() {

    }

    public User(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
