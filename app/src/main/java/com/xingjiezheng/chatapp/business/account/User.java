package com.xingjiezheng.chatapp.business.account;

import com.xingjiezheng.chatapp.util.UserUtils;

import java.io.Serializable;

/**
 * Created by XingjieZheng
 * on 2016/4/7.
 */
public class User implements Serializable {
    private static final int GENDER_MALE = 1;
    private static final int GENDER_FEMALE = 2;

    private int id = -1;

    private String nickName;

    private int gender = -1;

    private String avatar;

    public User() {

    }

    public User(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isIdValid() {
        return UserUtils.isUserIdValid(id);
    }
}
