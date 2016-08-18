package com.xingjiezheng.chatapp.EventBus;

import com.xingjiezheng.chatapp.communication.CommunicationMessageBean;

/**
 * Created by XingjieZheng
 * on 2016/8/4.
 */
public class EventType {

    public static class ReceiveMessageEvent {
        private CommunicationMessageBean communicationMessageBean;

        public ReceiveMessageEvent(CommunicationMessageBean communicationMessageBean) {
            this.communicationMessageBean = communicationMessageBean;
        }

        public CommunicationMessageBean getCommunicationMessageBean() {
            return communicationMessageBean;
        }
    }
}
