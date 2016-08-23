package com.xingjiezheng.chatapp.business.contacts.add;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.contacts.ContactsPresenter;
import com.xingjiezheng.chatapp.framework.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddContactsActivity extends BaseActivity implements AddContactsContract.View {

    @Bind(R.id.edit_input)
    EditText editInput;
    @Bind(R.id.txt_search_result)
    TextView txtSearchResult;

    private AddContactsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        ButterKnife.bind(this);
        setPresenter(new AddContactsPresenter(this, getLoaderManager()));
    }

    @OnClick(R.id.btn_search)
    void onClickSearch() {

        String content = editInput.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            return;
        }
        presenter.search(content);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setData(SearchUserBean searchUserBean) {
        if (searchUserBean == null) {
            return;
        }
        txtSearchResult.setVisibility(View.VISIBLE);
        if (searchUserBean.getUser() == null) {
            txtSearchResult.setText(searchUserBean.getMsg());
        } else {
            // TODO: 2016/8/23
            txtSearchResult.setText(searchUserBean.getUser().getNickName());
        }

    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void setPresenter(AddContactsContract.Presenter presenter) {
        this.presenter = presenter;

    }
}
