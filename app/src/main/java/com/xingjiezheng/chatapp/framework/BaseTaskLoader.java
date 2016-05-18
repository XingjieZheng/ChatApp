package com.xingjiezheng.chatapp.framework;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.api.RetrofitUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by XingjieZheng
 * on 2016/5/17.
 */
public abstract class BaseTaskLoader<T> extends AsyncTaskLoader<T> {

    private final int mTaskId;

    public BaseTaskLoader(Context context, int taskId) {
        super(context);
        this.mTaskId = taskId;
    }


    @Override
    public T loadInBackground() {
        T result = null;
        ApiService apiService = RetrofitUtils.getApiService();
        Call<T> call = run(apiService);
        try {
            Response<T> response = call.execute();
            result = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected abstract Call<T> run(ApiService apiService);

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
