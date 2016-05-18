package com.xingjiezheng.chatapp.api;

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
            Retrofit commonRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.serverUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = commonRetrofit.create(ApiService.class);
        }
        return apiService;
    }
}
