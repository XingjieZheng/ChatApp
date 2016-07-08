package com.xingjiezheng.chatapp.business.contacts;

import android.app.Activity;
import android.content.Context;

import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.framework.BasePresenter;
import com.xingjiezheng.chatapp.framework.BaseView;

import java.util.List;

/**
 * Created by XingjieZheng
 * on 2016/4/15.
 */
public interface ContactsContract {

    interface View extends BaseView<Presenter> {

        Context getContext();

        void showMessage(String message);

        void showProgress();

        void hideProgress();

        void setData(List<User> userList);

    }

    interface Presenter extends BasePresenter {

        void getContacts();

    }
}
