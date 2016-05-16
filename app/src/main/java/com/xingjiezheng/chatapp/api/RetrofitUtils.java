package com.xingjiezheng.chatapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XingjieZheng
 * on 2016/5/16.
 */
public class RetrofitUtils {

    private static Retrofit commonRetrofit;

    public static Retrofit getCommonRetrofit() {
        if (commonRetrofit == null) {
            commonRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.serverUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return commonRetrofit;
    }
}
