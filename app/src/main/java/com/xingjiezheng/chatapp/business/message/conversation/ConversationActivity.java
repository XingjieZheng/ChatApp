package com.xingjiezheng.chatapp.business.message.conversation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.message.Message;
import com.xingjiezheng.chatapp.business.message.list.MessagePresenter;
import com.xingjiezheng.chatapp.business.message.list.MessageRecyclerViewAdapter;
import com.xingjiezheng.chatapp.constants.Extras;
import com.xingjiezheng.chatapp.framework.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConversationActivity extends BaseActivity implements ConversationContract.View,
        ConversationRecyclerViewAdapter.OnListFragmentInteractionListener {

    @Bind(R.id.btnSend)
    Button btnSend;
    @Bind(R.id.edtInput)
    EditText edtInput;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private ConversationContract.Presenter presenter;
    private ConversationRecyclerViewAdapter adapter;
    private String theOtherUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        init();
        presenter.getConversation();
    }

    private void init() {
        ButterKnife.bind(this);
        theOtherUserId = getIntent().getStringExtra(Extras.EXTRA_USER_ID);
        if (theOtherUserId == null) {
            throw new RuntimeException("please pass an activity first to use this call");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConversationRecyclerViewAdapter(this, this);
        recyclerView.setAdapter(adapter);

        presenter = new ConversationPresenter(this, getLoaderManager());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        super.showToast(message);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setData(List<Message> list) {
        if (list != null) {
            adapter.setData(list);
        }
    }

    @Override
    public String getTheOtherUserId() {
        return theOtherUserId;
    }

    @Override
    public void setPresenter(ConversationContract.Presenter presenter) {

    }

    @Override
    public void onListFragmentInteraction(Message item, int position) {

    }
}
