package com.xingjiezheng.chatapp.business.contacts.add;


import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.framework.BaseBean;


import java.io.Serializable;

/**
 * 登录的初始信息
 */
public class SearchUserBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;


    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
