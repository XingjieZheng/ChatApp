package com.xingjiezheng.chatapp.business.contacts.add;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.framework.activity.BaseActivity;
import com.xingjiezheng.chatapp.glide.GlideCircleTransform;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddContactsActivity extends BaseActivity implements AddContactsContract.View {

    @Bind(R.id.edit_input)
    EditText editInput;
    @Bind(R.id.layout_result)
    View layoutResult;
    @Bind(R.id.img_Avatar)
    ImageView imgAvatar;
    @Bind(R.id.txt_Name)
    TextView txtName;
    @Bind(R.id.txt_result_empty)
    TextView txtResultEmpty;
    @Bind(R.id.layout_result_no_empty)
    View layoutResultNoEmpty;

    private AddContactsContract.Presenter presenter;

    private User searchUser;

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

    @OnClick(R.id.btn_add)
    void onClickAddContacts() {

        if (searchUser != null) {
            presenter.addContacts(searchUser.getId());
        }

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
        layoutResult.setVisibility(View.VISIBLE);
        if (searchUserBean.getUser() == null) {
            txtResultEmpty.setVisibility(View.VISIBLE);
            layoutResultNoEmpty.setVisibility(View.GONE);
            txtResultEmpty.setText(searchUserBean.getMsg());
        } else {
            txtResultEmpty.setVisibility(View.GONE);
            layoutResultNoEmpty.setVisibility(View.VISIBLE);
            searchUser = searchUserBean.getUser();
            txtName.setText(searchUser.getNickName());
            Glide.with(this)
                    .load(searchUser.getAvatar())
                    .transform(new GlideCircleTransform(this))
                    .into(imgAvatar);
        }

    }

    @Override
    public void showMessage(String message) {
        showToast(message);
    }

    @Override
    public void hideSearchResultLayout() {
        layoutResult.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(AddContactsContract.Presenter presenter) {
        this.presenter = presenter;

    }
}
