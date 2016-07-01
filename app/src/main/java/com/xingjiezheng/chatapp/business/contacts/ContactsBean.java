package com.xingjiezheng.chatapp.business.contacts;


import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.framework.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 登录的初始信息
 */
public class ContactsBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<User> userArrayList;

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }
}
