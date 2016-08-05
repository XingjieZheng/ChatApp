package com.xingjiezheng.chatapp.communication;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.xingjiezheng.chatapp.constants.Extras;

/**
 * Created by XingjieZheng
 * on 2016/7/22.
 */
public class CommunicationService extends IntentService {

    private static final String TAG = CommunicationService.class.getSimpleName();
    private static final String ACTION_CONNECT = "com.xingjiezheng.chatapp.communication.action.CONNECT";
    private static final String ACTION_SEND = "com.xingjiezheng.chatapp.communication.action.SEND";
    private static final String ACTION_CLOSE = "com.xingjiezheng.chatapp.communication.action.CLOSE";

    public CommunicationService() {
        super("CommunicationService");
    }

    public static void startActionConnect(Context context, String userId) {
        Intent intent = new Intent(context, CommunicationService.class);
        intent.setAction(ACTION_CONNECT);
        intent.putExtra(Extras.EXTRA_USER_ID, userId);
        context.startService(intent);
    }

    public static void startActionSend(Context context, String senderUserId, String receiverUserId, String message) {
        Intent intent = new Intent(context, CommunicationService.class);
        intent.setAction(ACTION_SEND);
        intent.putExtra(Extras.EXTRA_SENDER_USER_ID, senderUserId);
        intent.putExtra(Extras.EXTRA_RECEIVER_USER_ID, receiverUserId);
        intent.putExtra(Extras.EXTRA_MESSAGE, message);
        context.startService(intent);
    }

    public static void startActionClose(Context context) {
        Intent intent = new Intent(context, CommunicationService.class);
        intent.setAction(ACTION_CLOSE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_CONNECT: {
                    String userId = intent.getStringExtra(Extras.EXTRA_USER_ID);
                    if (userId != null) {
                        handleActionConnect(userId);
                    }
                }
                break;
                case ACTION_SEND: {
                    String senderUserId = intent.getStringExtra(Extras.EXTRA_SENDER_USER_ID);
                    String receiverUserId = intent.getStringExtra(Extras.EXTRA_RECEIVER_USER_ID);
                    String message = intent.getStringExtra(Extras.EXTRA_MESSAGE);
                    if (senderUserId != null && receiverUserId != null && message != null) {
                        handleActionSend(senderUserId, receiverUserId, message);
                    }
                }
                break;
                case ACTION_CLOSE: {
                    handleActionClose();
                }
                break;
            }
        }
    }

    private void handleActionConnect(String userId) {
        CommunicationManager.getInstance().connect(userId);
    }

    private void handleActionSend(String senderUserId, String receiverUserId, String message) {
        CommunicationManager.getInstance().sendMessage(senderUserId, receiverUserId, message);
    }

    private void handleActionClose() {
        CommunicationManager.getInstance().close();
    }
}
