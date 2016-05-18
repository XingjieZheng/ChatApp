package com.xingjiezheng.chatapp.framework;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.xingjiezheng.chatapp.api.ApiService;

import retrofit2.Call;

/**
 * Created by XingjieZheng
 * on 2016/4/4.
 */
public abstract class BaseTaskExecutor {


    protected LoaderManager loaderManager;
    protected Context context;

    public <T> void requestTask(final int taskId, @NonNull final ApiServiceTask<T> apiServiceTask) {

        checkNoNull();
        loaderManager.initLoader(taskId, null, new LoaderManager.LoaderCallbacks<T>() {

            @Override
            public Loader<T> onCreateLoader(int id, Bundle args) {
                return new BaseTaskLoader<T>(context, taskId) {
                    @Override
                    protected Call<T> run(ApiService apiService) {
                        return apiServiceTask.run(apiService);
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<T> loader, T data) {
                apiServiceTask.onLoadFinished(loader, data);
            }

            @Override
            public void onLoaderReset(Loader<T> loader) {

            }
        });
    }

    public abstract class ApiServiceTask<T> {
        public abstract Call<T> run(ApiService apiService);

        public abstract void onLoadFinished(Loader<T> loader, T data);
    }

    private void checkNoNull() {
        if (context == null || loaderManager == null) {
            throw new NullPointerException("context or loaderManager can not be null in BaseTaskExecutor");
        }
    }

}
