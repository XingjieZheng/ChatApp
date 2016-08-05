package com.xingjiezheng.chatapp.EventBus;

import com.xingjiezheng.chatapp.communication.MessageBean;

/**
 * Created by XingjieZheng
 * on 2016/8/4.
 */
public class EventType {

    public static class ReceiveMessageEvent {
        private MessageBean messageBean;

        public ReceiveMessageEvent(MessageBean messageBean) {
            this.messageBean = messageBean;
        }

        public MessageBean getMessageBean() {
            return messageBean;
        }
    }
}
