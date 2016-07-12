package com.xingjiezheng.chatapp.business.message.conversation;

import android.content.Context;

import com.xingjiezheng.chatapp.business.message.Message;
import com.xingjiezheng.chatapp.framework.BasePresenter;
import com.xingjiezheng.chatapp.framework.BaseView;

import java.util.List;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public interface ConversationContract {

    interface View extends BaseView<Presenter> {

        Context getContext();

        void showMessage(String message);

        void showProgress();

        void hideProgress();

        void setData(List<Message> list);

        String getTheOtherUserId();

    }

    interface Presenter extends BasePresenter {

        void getConversation();

    }
}
