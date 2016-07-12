package com.xingjiezheng.chatapp.business.message.conversation;


import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.business.message.Message;
import com.xingjiezheng.chatapp.framework.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public class ConversationBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Message> messageArrayList;

    public ArrayList<Message> getMessageArrayList() {
        return messageArrayList;
    }


}
