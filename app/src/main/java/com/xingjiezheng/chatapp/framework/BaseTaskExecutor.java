package com.xingjiezheng.chatapp.framework;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.business.account.Account;
import com.xingjiezheng.chatapp.business.account.AccountManager;

import retrofit2.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XingjieZheng
 * on 2016/4/4.
 */
public abstract class BaseTaskExecutor {

    private SparseArray<LoaderCallbacks> loaderCallbacksSparseArray;

    public BaseTaskExecutor() {
        loaderCallbacksSparseArray = new SparseArray<>();
    }

    public <T> void requestTask(final int taskId, @NonNull final ApiServiceTask<T> apiServiceTask) {
        requestTask(taskId, false, false, apiServiceTask);
    }

    public <T> void requestTask(final int taskId, boolean isForceLoad, @NonNull final ApiServiceTask<T> apiServiceTask) {
        requestTask(taskId, false, isForceLoad, apiServiceTask);
    }

    public <T> void requestTask(final int taskId, final boolean isWithCookie, boolean isForceLoad, @NonNull final ApiServiceTask<T> apiServiceTask) {
        Account account = AccountManager.getInstance().getLoginAccount();
        if (isWithCookie && (account == null || account.getCookieList() == null || account.getCookieList().size() < 2)) {
            apiServiceTask.onLoadFail("Error, cookie is null. Please login again!");
            return;
        }

        checkNotNull(getLoaderManager(), "loaderManager can not be null in BaseTaskExecutor");

        LoaderCallbacks loaderCallbacks = loaderCallbacksSparseArray.get(taskId);
        if (loaderCallbacks == null) {
            loaderCallbacks = new LoaderCallbacks<T>() {
                @Override
                public Loader<T> onCreateLoader(int id, Bundle args) {
                    // new task loader
                    return new BaseTaskLoader<T>(
                            checkNotNull(getContext(), "context can not be null in BaseTaskExecutor"),
                            taskId,
                            isWithCookie) {
                        @Override
                        protected Call<T> run(ApiService apiService) {
                            return apiServiceTask.run(apiService);
                        }
                    };
                }

                @Override
                public void onLoadFinished(Loader<T> loader, T data) {
                    if (data != null) {
                        apiServiceTask.onLoadSuccess(data);
                    } else {
                        apiServiceTask.onLoadFail("Error, response is null!");
                    }
                }

                @Override
                public void onLoaderReset(Loader<T> loader) {

                }
            };
            loaderCallbacksSparseArray.put(taskId, loaderCallbacks);
        }

        Loader loader = getLoaderManager().getLoader(taskId);
        if (loader == null) {
            getLoaderManager().initLoader(taskId, null, loaderCallbacks);
        } else {
            getLoaderManager().restartLoader(taskId, null, loaderCallbacks);
        }

    }

    public abstract class ApiServiceTask<T> {
        public abstract Call<T> run(ApiService apiService);

        public abstract void onLoadSuccess(T data);

        public abstract void onLoadFail(String errorMsg);
    }

    public abstract Context getContext();

    public abstract LoaderManager getLoaderManager();

//    public abstract void onLoaderCallbacks();


}
