package com.xingjiezheng.chatapp.api;

import com.xingjiezheng.chatapp.business.account.login.AccountLoginBean;
import com.xingjiezheng.chatapp.business.account.MyProfileBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public interface ApiService {

    String serverUrlTest = "http://10.10.152.73:8080/";

    @FormUrlEncoded
    @POST("account/login.do")
    Call<AccountLoginBean> login(@Field("mobile") String account, @Field("password") String password);


    @POST("account/cookieLogin.do")
    Call<AccountLoginBean> cookieLogin();


    @POST("user/myDetail_1_2_0.do")
    Call<MyProfileBean> getMyProfile();


}
