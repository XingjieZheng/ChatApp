package com.xingjiezheng.chatapp.communication;


import com.xingjiezheng.chatapp.business.message.Message;

import java.io.Serializable;

/**
 * Created by XingjieZheng
 * on 2016/6/17.
 */
public class CommunicationMessageBean implements Serializable {

    public static final int TYPE_NORMAL_MESSAGE = 0;
    public static final int TYPE_REGISTER_MESSAGE = 1;

    private Message message;
    private String securityKey;
    private int type;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
