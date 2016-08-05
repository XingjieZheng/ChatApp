package com.xingjiezheng.chatapp.communication;

import java.io.Serializable;

/**
 * Created by XingjieZheng
 * on 2016/6/17.
 */
public class MessageBean implements Serializable{

    public static final int TYPE_NORMAL_MESSAGE = 0;
    public static final int TYPE_REGISTER_MESSAGE = 1;

    private String senderUserId;
    private String receiverUserId;
    private String message;
    private String securityKey;
    private int type;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }
}
