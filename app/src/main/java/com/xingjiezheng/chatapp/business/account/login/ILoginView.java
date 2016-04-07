package com.xingjiezheng.chatapp.business.account.login;

import android.content.Context;
import android.view.View;

/**
 * Created by XingjieZheng
 * on 2016/4/7.
 */
public interface ILoginView {

    void showProgress();

    void hideProgress();

    String getAccount();

    String getPassword();

    void setAccountEditViewErrorTips(String message);

    void setPasswordEditViewErrorTips(String message);

    Context getContext();

    void requestFocusAccountEditView();

    void requestPasswordEditView();

}
