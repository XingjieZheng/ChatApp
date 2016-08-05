package com.xingjiezheng.chatapp.api;

import com.xingjiezheng.chatapp.business.cookie.CookieHandler;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public class RetrofitUtils {

    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.cookieJar(new CookieHandler());
            Retrofit commonRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.serverUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiService = commonRetrofit.create(ApiService.class);
        }
        return apiService;
    }

}
