package com.xingjiezheng.chatapp.api;

import com.xingjiezheng.chatapp.business.account.login.AccountLoginBean;
import com.xingjiezheng.chatapp.business.contacts.ContactsBean;

import java.net.InetAddress;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public interface ApiService {

    String serverUrlTest = "http://10.10.152.212:8080/";

    @FormUrlEncoded
    @POST("account/login.do")
    Call<AccountLoginBean> login(@Field("mobile") String account, @Field("password") String password);


    @POST("account/cookieLogin.do")
    Call<AccountLoginBean> cookieLogin();


    @POST("contacts/getContacts.do")
    Call<ContactsBean> getContacts();


}
