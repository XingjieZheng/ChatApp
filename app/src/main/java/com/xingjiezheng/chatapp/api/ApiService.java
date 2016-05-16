package com.xingjiezheng.chatapp.api;

import com.xingjiezheng.chatapp.business.account.login.AccountLoginBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public interface ApiService {

    String serverUrl = "http://coffeeapi.yuanlai.com";

    @FormUrlEncoded
    @POST("account/login.do")
    Call<AccountLoginBean> login(@Field("mobile") String account, @Field("password") String password);
}
