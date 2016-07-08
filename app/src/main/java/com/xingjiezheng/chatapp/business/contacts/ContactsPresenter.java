package com.xingjiezheng.chatapp.business.contacts;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.api.TaskId;
import com.xingjiezheng.chatapp.framework.BaseTaskExecutor;
import com.xingjiezheng.chatapp.util.LogUtils;

import retrofit2.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XingjieZheng
 * on 2016/7/8.
 */
public class ContactsPresenter extends BaseTaskExecutor implements ContactsContract.Presenter {

    private static final String TAG = ContactsPresenter.class.getSimpleName();

    private ContactsContract.View contractView;
    private LoaderManager loaderManager;
    private Context context;

    public ContactsPresenter(@NonNull ContactsContract.View contractView, @NonNull LoaderManager loaderManager) {
        this.contractView = checkNotNull(contractView, "contractView cannot be null!");
        this.loaderManager = checkNotNull(loaderManager, "loaderManager cannot be null");
        this.context = contractView.getContext();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public LoaderManager getLoaderManager() {
        return loaderManager;
    }

    @Override
    public void getContacts() {
        contractView.showProgress();
        requestTask(TaskId.CONTACTS, true, new ApiServiceTask<ContactsBean>() {

            @Override
            public Call<ContactsBean> run(ApiService apiService) {
                return apiService.getContacts();
            }

            @Override
            public void onLoadSuccess(@NonNull ContactsBean data) {
                contractView.hideProgress();
                contractView.setData(data.getUserArrayList());
                LogUtils.LOGI(TAG, data.toString() + " " + System.currentTimeMillis());
            }

            @Override
            public void onLoadFail(String errorMsg) {
                contractView.hideProgress();
                contractView.showMessage(errorMsg);
            }
        });
    }

}
