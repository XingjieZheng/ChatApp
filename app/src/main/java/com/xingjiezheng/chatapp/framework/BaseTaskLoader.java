package com.xingjiezheng.chatapp.framework;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.google.gson.Gson;
import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.api.RetrofitUtils;
import com.xingjiezheng.chatapp.util.LogUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by XingjieZheng
 * on 2016/5/17.
 */
public abstract class BaseTaskLoader<T> extends AsyncTaskLoader<T> {

    private static final String TAG = LogUtils.makeLogTag(BaseTaskLoader.class);
    private final int mTaskId;
    private final boolean isWithCookie;

    public BaseTaskLoader(Context context, int taskId, boolean isWithCookie) {
        super(context);
        this.mTaskId = taskId;
        this.isWithCookie = isWithCookie;
    }

    @Override
    public T loadInBackground() {
        T result = null;
        ApiService apiService;
        apiService = RetrofitUtils.getApiService();
        Call<T> call = run(apiService);
        try {
            Response<T> response = call.execute();
            result = response.body();
            if (LogUtils.LOGGING_ENABLED) {
                if (result != null) {
                    Gson gson = new Gson();
                    String responsePrint = gson.toJson(result);
                    String headersPrint = gson.toJson(response.headers());
                    LogUtils.LOGI(TAG, "url:" + call.request().url()
                            + "\nheaders:" + headersPrint
                            + "\nresponse:" + responsePrint);
                } else {
                    LogUtils.LOGI(TAG, mTaskId + " response is null");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected abstract Call<T> run(ApiService apiService);

    @Override
    protected void onStartLoading() {
//        LogUtils.LOGE(TAG, "onStartLoading()");
        forceLoad();
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
//        LogUtils.LOGE(TAG, "forceLoad()");
    }


    @Override
    protected void onReset() {
        super.onReset();
//        LogUtils.LOGE(TAG, "onReset()");
    }

    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
//        LogUtils.LOGE(TAG, "deliverResult()");
    }
}
