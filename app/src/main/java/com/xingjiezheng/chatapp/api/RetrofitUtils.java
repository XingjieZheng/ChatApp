package com.xingjiezheng.chatapp.api;

import com.xingjiezheng.chatapp.business.account.Account;
import com.xingjiezheng.chatapp.business.account.AccountManager;
import com.xingjiezheng.chatapp.util.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
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
    private static List<Cookie> cookieList;

    public static ApiService getApiServiceWithoutCookie() {
        if (apiServiceWithoutCookie == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.cookieJar(new CookieHandler());
            Retrofit commonRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.serverUrlTest)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            apiServiceWithoutCookie = commonRetrofit.create(ApiService.class);
        }
        return apiServiceWithoutCookie;
    }

    public static ApiService getApiServiceWithCookie() {
        List<Cookie> newCookieList = AccountManager.getInstance().getLoginAccount().getCookieList();
        if (apiServiceWithCookie == null || !cookieList.equals(newCookieList)) {
            cookieList = newCookieList;
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//            httpClient.addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request original = chain.request();
//                    Request request = original.newBuilder()
//                            .build();
//                    return chain.proceed(request);
//                }
//            });
            // TODO: 2016/6/30
            httpClient.cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    return cookieList == null ? new ArrayList<Cookie>() : cookieList;
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
