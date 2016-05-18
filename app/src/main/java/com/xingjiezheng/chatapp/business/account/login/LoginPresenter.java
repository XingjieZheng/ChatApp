package com.xingjiezheng.chatapp.business.account.login;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.api.TaskId;
import com.xingjiezheng.chatapp.framework.BaseTaskExecutor;
import com.xingjiezheng.chatapp.util.LogUtils;

import retrofit2.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XingjieZheng
 * on 2016/4/7.
 */
public class LoginPresenter extends BaseTaskExecutor implements LoginContract.Presenter {

    private static final String TAG = LogUtils.makeLogTag(LoginPresenter.class);

    private LoginContract.View loginView;
    private LoaderManager loaderManager;
    private Context context;

    public LoginPresenter(@NonNull LoginContract.View loginView, @NonNull LoaderManager loaderManager) {
        this.loginView = checkNotNull(loginView, "loginView cannot be null!");
        this.loaderManager = checkNotNull(loaderManager, "loaderManager cannot be null");
        this.context = loginView.getContext();
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
        } else if (!isAccountValid(account)) {
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
            loginOnServer(account, password);
        }
    }


    private void loginOnServer(final String account, final String password) {
        // TODO: 2016/5/17
        requestTask(TaskId.LOGIN, new ApiServiceTask<AccountLoginBean>() {
            @Override
            public Call<AccountLoginBean> run(ApiService apiService) {
                return apiService.login(account, password);
            }

            @Override
            public void onLoadFinished(Loader<AccountLoginBean> loader, AccountLoginBean data) {
                loginView.hideProgress();
                if (data.isStatusSuccess()) {
                    loginView.showLoginMessage(data.toString());
                    LogUtils.LOGI(TAG, loader.getId() + " " + data.toString() + " " + System.currentTimeMillis());
                } else {
                    loginView.showLoginMessage(data.getMsg());
                    LogUtils.LOGI(TAG, loader.getId() + " " + data.getMsg() + " " + System.currentTimeMillis());
                }
            }
        });
    }


    @Override
    public boolean isAccountValid(String account) {
        return account != null && account.length() == 11;
    }

    @Override
    public boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() > 4;
    }

    @Override
    public void start() {

    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public LoaderManager getLoaderManager() {
        return loaderManager;
    }
}
