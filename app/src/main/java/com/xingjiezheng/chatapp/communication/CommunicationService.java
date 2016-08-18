package com.xingjiezheng.chatapp.communication;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.xingjiezheng.chatapp.constants.Extras;
import com.xingjiezheng.chatapp.util.UserUtils;

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

    public static void startActionConnect(Context context, int userId) {
        Intent intent = new Intent(context, CommunicationService.class);
        intent.setAction(ACTION_CONNECT);
        intent.putExtra(Extras.EXTRA_USER_ID, userId);
        context.startService(intent);
    }

    public static void startActionSend(Context context, int senderUserId, int receiverUserId, String message) {
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
                    int userId = intent.getIntExtra(Extras.EXTRA_USER_ID, 0);
                    if (isUserIdValid(userId)) {
                        handleActionConnect(userId);
                    }
                }
                break;
                case ACTION_SEND: {
                    int senderUserId = intent.getIntExtra(Extras.EXTRA_SENDER_USER_ID, 0);
                    int receiverUserId = intent.getIntExtra(Extras.EXTRA_RECEIVER_USER_ID, 0);
                    String message = intent.getStringExtra(Extras.EXTRA_MESSAGE);
                    if (isUserIdValid(senderUserId) && isUserIdValid(receiverUserId) && message != null) {
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

    private void handleActionConnect(int userId) {
        CommunicationManager.getInstance().connect(userId);
    }

    private void handleActionSend(int senderUserId, int receiverUserId, String message) {
        CommunicationManager.getInstance().sendMessage(senderUserId, receiverUserId, message);
    }

    private void handleActionClose() {
        CommunicationManager.getInstance().close();
    }

    private boolean isUserIdValid(int userId) {
        return UserUtils.isUserIdValid(userId);
    }
}
