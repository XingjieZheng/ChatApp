package com.xingjiezheng.chatapp.communication;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xingjiezheng.chatapp.util.LogUtils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by XingjieZheng
 * on 2016/7/22.
 */
public class CommunicationManager {

    private static final String TAG = CommunicationManager.class.getSimpleName();

    private static final String WEB_SOCKET_SERVER_URL = "ws://10.10.152.67:8080/websocket";
    private static final String WEB_SOCKET_SECURITY_KEY = "Jekjg8k2eaj3j5fjafj4";

    private static CommunicationManager communicationManager;
    private WebSocketClient webSocketClient;
    private String userId;
    private MessageBean messageBeanSend;
    private MessageBean messageBeanReceive;
    private Gson gson;

    private CommunicationManager() {
        messageBeanSend = new MessageBean();
        gson = new Gson();
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

    public boolean connect(String userId) {
        if (webSocketClient != null) {
            webSocketClient = null;
        }
        try {
            this.userId = userId;
            webSocketClient = new WebSocketClient(new URI(WEB_SOCKET_SERVER_URL), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    LogUtils.LOGI(TAG, "onOpen");
                    sendUserInfo();
                }

                @Override
                public void onMessage(final String s) {
                    try {
                        messageBeanReceive = gson.fromJson(s, MessageBean.class);
                        if (messageBeanReceive != null && messageBeanReceive.getMessage() != null) {
                            LogUtils.LOGI(TAG, messageBeanReceive.getMessage());
                            // TODO: 2016/7/22
                        }
                    } catch (Exception e) {
                        LogUtils.LOGE(TAG, "Error: " + s);
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    LogUtils.LOGI(TAG, "onClose():" + i + " " + s + " " + b);
                }

                @Override
                public void onError(Exception e) {
                    LogUtils.LOGE(TAG, "onError" + e.toString());
                }
            };
            webSocketClient.connect();
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            LogUtils.LOGE(TAG, "URISyntaxException " + e.toString());
            return false;
        }
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
        boolean isConnectSuccessfully = true;
        if (webSocketClient == null) {
            isConnectSuccessfully = connect(senderUserId);
        }
        if (isConnectSuccessfully) {
            messageBeanSend.setReceiverUserId(receiverUserId);
            messageBeanSend.setSenderUserId(senderUserId);
            messageBeanSend.setMessage(message);
            messageBeanSend.setType(MessageBean.TYPE_NORMAL_MESSAGE);
            webSocketClient.send(gson.toJson(messageBeanSend));
        }
    }

}