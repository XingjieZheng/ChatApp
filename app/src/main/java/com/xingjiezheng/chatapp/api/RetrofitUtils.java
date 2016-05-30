package com.xingjiezheng.chatapp.api;

import com.xingjiezheng.chatapp.business.account.Account;
import com.xingjiezheng.chatapp.business.account.AccountManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public class RetrofitUtils {

    private static ApiService apiServiceWithoutCookie;
    private static ApiService apiServiceWithCookie;
    private static String cookie;


    public static ApiService getApiServiceWithoutCookie() {
        if (apiServiceWithoutCookie == null) {
            Retrofit commonRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.serverUrlTest)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiServiceWithoutCookie = commonRetrofit.create(ApiService.class);
        }
        return apiServiceWithoutCookie;
    }

    public static ApiService getApiServiceWithCookie() {
        String newCookie = AccountManager.getInstance().getLoginAccount().getCookie();
        if (cookie == null) {
            cookie = newCookie;
        }
        if (apiServiceWithCookie == null || !cookie.equals(newCookie)) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Cookie", cookie)
                            .build();
                    return chain.proceed(request);
                }
            });

            Retrofit commonRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.serverUrlTest)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiServiceWithCookie = commonRetrofit.create(ApiService.class);
        }
        return apiServiceWithCookie;
    }
}
