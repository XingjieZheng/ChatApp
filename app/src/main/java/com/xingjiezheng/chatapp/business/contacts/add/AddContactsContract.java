package com.xingjiezheng.chatapp.business.contacts.add;

import android.content.Context;

import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.framework.BasePresenter;
import com.xingjiezheng.chatapp.framework.BaseView;

import java.util.List;

/**
 * Created by XingjieZheng
 * on 2016/4/15.
 */
public interface AddContactsContract {

    interface View extends BaseView<Presenter> {

        Context getContext();

        void showProgress();

        void hideProgress();

        void setData(SearchUserBean searchUserBean);

        void showMessage(String message);

    }

    interface Presenter extends BasePresenter {

        void search(String userId);

    }
}
