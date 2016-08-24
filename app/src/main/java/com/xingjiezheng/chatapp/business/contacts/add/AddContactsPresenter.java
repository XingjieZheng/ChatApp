package com.xingjiezheng.chatapp.business.contacts.add;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import com.xingjiezheng.chatapp.api.ApiService;
import com.xingjiezheng.chatapp.api.TaskId;
import com.xingjiezheng.chatapp.business.contacts.ContactsContract;
import com.xingjiezheng.chatapp.business.contacts.ContactsListBean;
import com.xingjiezheng.chatapp.framework.BaseBean;
import com.xingjiezheng.chatapp.framework.BaseTaskExecutor;
import com.xingjiezheng.chatapp.util.LogUtils;

import retrofit2.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by XingjieZheng
 * on 2016/7/8.
 */
public class AddContactsPresenter extends BaseTaskExecutor implements AddContactsContract.Presenter {

    private static final String TAG = AddContactsPresenter.class.getSimpleName();

    private AddContactsContract.View contractView;
    private LoaderManager loaderManager;
    private Context context;
    private String userId;

    public AddContactsPresenter(@NonNull AddContactsContract.View contractView, @NonNull LoaderManager loaderManager) {
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
    public void search(String userId) {
        contractView.showProgress();
        this.userId = userId;
        requestTask(TaskId.SEARCH_USER, true, new ApiServiceTask<SearchUserBean>() {

            @Override
            public Call<SearchUserBean> run(ApiService apiService) {
                return apiService.searchUser(AddContactsPresenter.this.userId);
            }

            @Override
            public void onLoadSuccess(@NonNull SearchUserBean data) {
                contractView.hideProgress();
                contractView.setData(data);
            }

            @Override
            public void onLoadFail(String errorMsg) {
                contractView.hideProgress();
                contractView.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void addContacts(int userId) {
        contractView.showProgress();
        this.userId = String.valueOf(userId);
        requestTask(TaskId.ADD_CONTACTS, true, new ApiServiceTask<BaseBean>() {

            @Override
            public Call<BaseBean> run(ApiService apiService) {
                return apiService.requestBecomeContacts(AddContactsPresenter.this.userId);
            }

            @Override
            public void onLoadSuccess(@NonNull BaseBean data) {
                contractView.hideProgress();
                if (data.isStatusSuccess()) {
                    contractView.hideSearchResultLayout();
                }
                contractView.showMessage(data.getMsg());
            }

            @Override
            public void onLoadFail(String errorMsg) {
                contractView.hideProgress();
                contractView.showMessage(errorMsg);
            }
        });
    }
}
