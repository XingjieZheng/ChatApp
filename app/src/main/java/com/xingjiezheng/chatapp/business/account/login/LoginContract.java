package com.xingjiezheng.chatapp.business.account.login;

import android.app.Activity;
import android.content.Context;

import com.xingjiezheng.chatapp.framework.BasePresenter;
import com.xingjiezheng.chatapp.framework.BaseView;

/**
 * Created by XingjieZheng
 * on 2016/4/15.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        String getAccount();

        String getPassword();

        void setAccountEditViewErrorTips(String message);

        void setPasswordEditViewErrorTips(String message);

        Context getContext();

        void requestFocusAccountEditView();

        void requestPasswordEditView();

        void showLoginMessage(String message);

        void gotoActivityAndFinishMyself(Class<? extends Activity> activityClass);

    }

    interface Presenter extends BasePresenter {

        void login();

        boolean isAccountValid(String account);

        boolean isPasswordValid(String password);

        void registerCommunicationService(int userId);

    }
}
