package com.xingjiezheng.chatapp.business.message.conversation;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.api.TaskId;
import com.xingjiezheng.chatapp.business.message.list.MessageListBean;
import com.xingjiezheng.chatapp.framework.BaseTaskExecutor;
import com.xingjiezheng.chatapp.util.LogUtils;

import retrofit2.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public class ConversationPresenter extends BaseTaskExecutor implements ConversationContract.Presenter {

    private static final String TAG = ConversationPresenter.class.getSimpleName();

    private ConversationContract.View contractView;
    private LoaderManager loaderManager;
    private Context context;

    public ConversationPresenter(@NonNull ConversationContract.View contractView, @NonNull LoaderManager loaderManager) {
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
    public void getConversation() {
        contractView.showProgress();
        requestTask(TaskId.CONVERSATION, true, new ApiServiceTask<ConversationBean>() {

            @Override
            public Call<ConversationBean> run(ApiService apiService) {
                return apiService.getConversation(contractView.getTheOtherUserId());
            }

            @Override
            public void onLoadSuccess(@NonNull ConversationBean data) {
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
