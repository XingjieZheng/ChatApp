package com.xingjiezheng.chatapp.business.message;

import com.xingjiezheng.chatapp.business.account.User;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public class Message {

    private String id;
    private long time;
    private String content;
    private int unReadCount;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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
