package com.xingjiezheng.chatapp.api;

import com.xingjiezheng.chatapp.business.account.login.AccountLoginBean;
import com.xingjiezheng.chatapp.business.contacts.ContactsListBean;
import com.xingjiezheng.chatapp.business.contacts.add.SearchUserBean;
import com.xingjiezheng.chatapp.business.message.conversation.ConversationBean;
import com.xingjiezheng.chatapp.business.message.conversation.ConversationContract;
import com.xingjiezheng.chatapp.business.message.list.MessageListBean;
import com.xingjiezheng.chatapp.framework.BaseBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public interface ApiService {

    String serverUrl = "http://10.10.152.187:8081/";
    String loginApi = "account/login.do";

    @FormUrlEncoded
    @POST(loginApi)
    Call<AccountLoginBean> login(@Field("mobile") String account, @Field("password") String password);


    @POST("account/cookieLogin.do")
    Call<AccountLoginBean> cookieLogin();


    @POST("contacts/getContacts.do")
    Call<ContactsListBean> getContacts();

    @FormUrlEncoded
    @POST("contacts/requestBecomeContacts.do")
    Call<BaseBean> requestBecomeContacts(@Field("requestedUserId") String requestedUserId);

    @POST("message/getMessage.do")
    Call<MessageListBean> getMessage();

    @FormUrlEncoded
    @POST("message/getConversation.do")
    Call<ConversationBean> getConversation(@Field("theOtherUserId") String theOtherUserId);

    @FormUrlEncoded
    @POST("user/searchUser.do")
    Call<SearchUserBean> searchUser(@Field("userId") String userId);
}
