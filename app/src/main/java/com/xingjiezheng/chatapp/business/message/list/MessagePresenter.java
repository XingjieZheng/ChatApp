package com.xingjiezheng.chatapp.business.message.list;

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
 * on 2016/7/11.
 */
public class MessagePresenter extends BaseTaskExecutor implements MessageContract.Presenter {

    private static final String TAG = MessagePresenter.class.getSimpleName();

    private MessageContract.View contractView;
    private LoaderManager loaderManager;
    private Context context;

    public MessagePresenter(@NonNull MessageContract.View contractView, @NonNull LoaderManager loaderManager) {
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
        requestTask(TaskId.MESSAGE, true, new ApiServiceTask<MessageListBean>() {

            @Override
            public Call<MessageListBean> run(ApiService apiService) {
                return apiService.getMessage();
            }

            @Override
            public void onLoadSuccess(@NonNull MessageListBean data) {
                contractView.hideProgress();
                contractView.setData(data.getMessageArrayList());
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
