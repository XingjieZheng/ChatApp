package com.xingjiezheng.chatapp.business.message;

import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.framework.BaseToStringInstance;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public class Message extends BaseToStringInstance implements Serializable {

    private String id;
    private Date time;
    private String content;
    private int unReadCount;
    private User sender;
    private User receiver;

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

}
