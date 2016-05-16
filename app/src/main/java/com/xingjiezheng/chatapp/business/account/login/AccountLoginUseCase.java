package com.xingjiezheng.chatapp.business.account.login;

import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.api.RetrofitUtils;
import com.xingjiezheng.chatapp.usecase.UseCase;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public class AccountLoginUseCase extends UseCase<AccountLoginUseCase.RequestValues, AccountLoginUseCase.ResponseValue> {


    @Override
    protected void executeUseCase(RequestValues requestValues) {

        ApiService apiService = RetrofitUtils.getCommonRetrofit().create(ApiService.class);
        Call<AccountLoginBean> call = apiService.login(requestValues.getAccount(), requestValues.getPassword());
        try {
            Response<AccountLoginBean> response = call.execute();
            AccountLoginBean bean = response.body();
            if (bean != null) {
                getUseCaseCallback().onSuccess(new ResponseValue(bean));
            } else {
                getUseCaseCallback().onError(new Error("Error, data is empty!"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            getUseCaseCallback().onError(new Error(e.toString()));
        }

    }

    public static class RequestValues extends UseCase.RequestValues {

        private String account;
        private String password;

        public RequestValues(String account, String password) {
            this.account = account;
            this.password = password;
        }

        public String getAccount() {
            return account;
        }

        public String getPassword() {
            return password;
        }

    }

    public static class ResponseValue extends UseCase.ResponseValue {

        private AccountLoginBean accountLoginBean;

        public ResponseValue(AccountLoginBean accountLoginBean) {
            this.accountLoginBean = accountLoginBean;
        }

        public AccountLoginBean getAccountLoginBean() {
            return accountLoginBean;
        }
    }
}
