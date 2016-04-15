package com.xingjiezheng.chatapp.business.account.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.util.LogUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XingjieZheng
 * on 2016/4/7.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LogUtils.makeLogTag(LoginPresenter.class);

    private LoginContract.View loginView;

    public LoginPresenter(@NonNull LoginContract.View loginView) {
        this.loginView = checkNotNull(loginView, "loginView cannot be null!");
        loginView.setPresenter(this);
    }

    @Override
    public void login() {
        LogUtils.LOGI(TAG, "attemptLogin");

        loginView.setAccountEditViewErrorTips(null);
        loginView.setPasswordEditViewErrorTips(null);

        String account = loginView.getAccount();
        String password = loginView.getPassword();

        boolean cancel = false;
        boolean focusViewIsAccountEditView = false;


        if (TextUtils.isEmpty(password)) {
            loginView.setPasswordEditViewErrorTips(loginView.getContext().getString(R.string.error_field_required));
            focusViewIsAccountEditView = false;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            loginView.setPasswordEditViewErrorTips(loginView.getContext().getString(R.string.error_invalid_password));
            focusViewIsAccountEditView = false;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(account)) {
            loginView.setAccountEditViewErrorTips(loginView.getContext().getString(R.string.error_field_required));
            focusViewIsAccountEditView = true;
            cancel = true;
        } else if (!isEmailValid(account)) {
            loginView.setAccountEditViewErrorTips(loginView.getContext().getString(R.string.error_invalid_account));
            focusViewIsAccountEditView = true;
            cancel = true;
        }

        if (cancel) {
            if (focusViewIsAccountEditView) {
                loginView.requestFocusAccountEditView();
            } else {
                loginView.requestPasswordEditView();
            }
        } else {
            loginView.showProgress();
            // TODO: 2016/4/7  request net login here
        }
    }

    @Override
    public boolean isEmailValid(String email) {
        return email.contains("1");
    }

    @Override
    public boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() > 4;
    }

    @Override
    public void start() {

    }
}
