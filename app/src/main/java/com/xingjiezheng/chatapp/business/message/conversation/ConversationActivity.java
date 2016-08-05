package com.xingjiezheng.chatapp.business.message.conversation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;

import com.xingjiezheng.chatapp.EventBus.EventType;
import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.business.message.Message;
import com.xingjiezheng.chatapp.communication.MessageBean;
import com.xingjiezheng.chatapp.constants.Extras;
import com.xingjiezheng.chatapp.framework.activity.BaseHandlerActivity;
import com.xingjiezheng.chatapp.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversationActivity extends BaseHandlerActivity implements ConversationContract.View,
        ConversationRecyclerViewAdapter.OnListFragmentInteractionListener {

    private static final String TAG = ConversationActivity.class.getSimpleName();

    @Bind(R.id.edtInput)
    EditText edtInput;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private ConversationContract.Presenter presenter;
    private ConversationRecyclerViewAdapter adapter;
    private String theOtherUserId;

    private List<Message> list;

    private static final int HANDLER_RECEIVE_MESSAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        init();
        presenter.getConversation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        theOtherUserId = getIntent().getStringExtra(Extras.EXTRA_USER_ID);
        if (theOtherUserId == null) {
            throw new RuntimeException("Error, param 'theOtherUserId' is null!");
        }
        list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConversationRecyclerViewAdapter(this, this, list);
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
            this.list.addAll(list);
            adapter.notifyDataSetChanged();
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

    @OnClick(R.id.btnSend)
    void clickBtnSend() {
        String context = edtInput.getText().toString();
        if (TextUtils.isEmpty(context)) {
            return;
        }
        presenter.sendMessage(context);
    }

    @Subscribe
    public void onEvent(EventType.ReceiveMessageEvent event) {
        if (theOtherUserId == null || event.getMessageBean() == null || !theOtherUserId.equals(event.getMessageBean().getReceiverUserId())) {
            return;
        }
        android.os.Message handlerMessage = handler.obtainMessage(HANDLER_RECEIVE_MESSAGE);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Extras.EXTRA_DATA, event.getMessageBean());
        handlerMessage.setData(bundle);
        handler.sendMessage(handlerMessage);
        LogUtils.LOGI(TAG, event.getMessageBean().getMessage());
    }

    @Override
    public boolean handleMessage(android.os.Message msg) {
        switch (msg.what) {
            case HANDLER_RECEIVE_MESSAGE:
                if (msg.getData() == null) {
                    return false;
                }
                MessageBean messageBean = (MessageBean) msg.getData().getSerializable(Extras.EXTRA_DATA);
                if (messageBean == null) {
                    return false;
                }
                Message message = new Message();
                message.setContent(messageBean.getMessage());
                message.setTime(messageBean.getTime());
                message.setUser(new User(messageBean.getSenderUserId()));
                list.add(message);
                adapter.notifyDataSetChanged();
                return true;
        }
        return false;
    }

}
