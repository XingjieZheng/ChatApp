package com.xingjiezheng.chatapp.framework;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.xingjiezheng.chatapp.api.ApiService;

import retrofit2.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XingjieZheng
 * on 2016/4/4.
 */
public abstract class BaseTaskExecutor {


    public <T> void requestTask(final int taskId, @NonNull final ApiServiceTask<T> apiServiceTask) {


        checkNotNull(getLoaderManager(), "loaderManager can not be null in BaseTaskExecutor")
                .initLoader(taskId, null, new LoaderManager.LoaderCallbacks<T>() {

                    @Override
                    public Loader<T> onCreateLoader(int id, Bundle args) {
                        return new BaseTaskLoader<T>(
                                checkNotNull(getContext(), "context can not be null in BaseTaskExecutor"),
                                taskId) {
                            @Override
                            protected Call<T> run(ApiService apiService) {
                                return apiServiceTask.run(apiService);
                            }
                        };
                    }

                    @Override
                    public void onLoadFinished(Loader<T> loader, T data) {
                        if (data != null) {
                            apiServiceTask.onLoadSuccess(loader, data);
                        } else {
                            apiServiceTask.onLoadFail("Error, response is null!");
                        }
                    }

                    @Override
                    public void onLoaderReset(Loader<T> loader) {

                    }
                });
    }

    public abstract class ApiServiceTask<T> {
        public abstract Call<T> run(ApiService apiService);

        public abstract void onLoadSuccess(Loader<T> loader, T data);

        public abstract void onLoadFail(String errorMsg);
    }


    public abstract Context getContext();

    public abstract LoaderManager getLoaderManager();

}
