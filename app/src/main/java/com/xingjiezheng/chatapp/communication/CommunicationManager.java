package com.xingjiezheng.chatapp.communication;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xingjiezheng.chatapp.EventBus.EventType;
import com.xingjiezheng.chatapp.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;

/**
 * Created by XingjieZheng
 * on 2016/7/22.
 */
public class CommunicationManager {

    private static final String TAG = CommunicationManager.class.getSimpleName();

    private static final String WEB_SOCKET_SERVER_URL = "ws://10.10.152.187:8080/websocket";
    private static final String WEB_SOCKET_SECURITY_KEY = "Jekjg8k2eaj3j5fjafj4";

    private static CommunicationManager communicationManager;
    private WebSocketClient webSocketClient;
    private String userId;
    private MessageBean messageBeanSend;
    private MessageBean messageBeanReceive;
    private Gson gson;
    private int connectState;
    private static final int CONNECT_STATE_UNCONNECTED = 0;
    private static final int CONNECT_STATE_FAIL = 1;
    private static final int CONNECT_STATE_SUCCESSFULLY = 2;
    private static final int CONNECT_STATE_CLOSE = 3;

    private CommunicationManager() {
        messageBeanSend = new MessageBean();
        gson = new Gson();
        setConnectState(CONNECT_STATE_UNCONNECTED);
    }

    public static CommunicationManager getInstance() {
        if (communicationManager == null) {
            synchronized (CommunicationManager.class) {
                if (communicationManager == null) {
                    communicationManager = new CommunicationManager();
                }
            }
        }
        return communicationManager;
    }

    public void connect(String userId) {
        if (webSocketClient != null) {
            webSocketClient = null;
        }
        try {
            this.userId = userId;
            webSocketClient = new WebSocketClient(new URI(WEB_SOCKET_SERVER_URL), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    setConnectState(CONNECT_STATE_SUCCESSFULLY);
                    LogUtils.LOGI(TAG, "onOpen");
                    sendUserInfo();
                }

                @Override
                public void onMessage(final String s) {
                    try {
                        messageBeanReceive = gson.fromJson(s, MessageBean.class);
                        if (messageBeanReceive != null && messageBeanReceive.getMessage() != null) {
                            LogUtils.LOGI(TAG, messageBeanReceive.getMessage());
                            EventBus.getDefault().post(new EventType.ReceiveMessageEvent(messageBeanReceive));
                            // TODO: 2016/7/22
                        }
                    } catch (Exception e) {
                        LogUtils.LOGE(TAG, "Error: " + s);
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    LogUtils.LOGI(TAG, "onClose():" + i + " " + s + " " + b);
                    setConnectState(CONNECT_STATE_CLOSE);
                }

                @Override
                public void onError(Exception e) {
                    LogUtils.LOGE(TAG, "onError" + e.toString());
                }
            };
        } catch (URISyntaxException e) {
            setConnectState(CONNECT_STATE_FAIL);
            LogUtils.LOGE(TAG, "URISyntaxException " + e.toString());
            e.printStackTrace();
        }
        webSocketClient.connect();
    }

    private void sendUserInfo() {
        messageBeanSend.setType(MessageBean.TYPE_REGISTER_MESSAGE);
        messageBeanSend.setMessage("");
        messageBeanSend.setSenderUserId(userId);
        messageBeanSend.setSecurityKey(WEB_SOCKET_SECURITY_KEY);
        webSocketClient.send(gson.toJson(messageBeanSend));
    }

    public void sendMessage(String senderUserId, String receiverUserId, String message) {
        if (TextUtils.isEmpty(message) || TextUtils.isEmpty(senderUserId) || TextUtils.isEmpty(receiverUserId)) {
            LogUtils.LOGE(TAG, "sendMessage()  Error,param is null!");
            return;
        }
        if (!isConnectSuccessfully(userId)) {
            connect(senderUserId);
        }
        messageBeanSend.setReceiverUserId(receiverUserId);
        messageBeanSend.setSenderUserId(senderUserId);
        messageBeanSend.setMessage(message);
        messageBeanSend.setType(MessageBean.TYPE_NORMAL_MESSAGE);
        String messageJson = gson.toJson(messageBeanSend);
        LogUtils.LOGI(TAG, "send message:" + messageJson);
        try {
            webSocketClient.send(messageJson);
        } catch (NotYetConnectedException e) {
            e.printStackTrace();
            LogUtils.LOGE(TAG, e.toString());
            // TODO: 2016/8/3 show message:send message fail
        }
    }

    public void close() {
        if (webSocketClient != null) {
            webSocketClient.close();
        }
        setConnectState(CONNECT_STATE_UNCONNECTED);
    }

    private void setConnectState(int state) {
        this.connectState = state;
    }

    private boolean isConnectSuccessfully(String userId) {
        return this.connectState == CONNECT_STATE_SUCCESSFULLY && userId != null && userId.equals(this.userId);
    }

}