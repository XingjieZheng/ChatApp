package com.xingjiezheng.chatapp.business.message;

import com.xingjiezheng.chatapp.business.account.User;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
//@Entity
public class Message/* extends BaseToStringInstance */ {

//    @Id(autoincrement = true)
    private long id;
//    @NotNull
    private long time;
//    @NotNull
    private String content;
    private int unReadCount;

//    @NotNull
//    @ToOne(joinProperty = "senderId")
    private User sender;
//    @NotNull
//    @ToOne(joinProperty = "receiverId")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
