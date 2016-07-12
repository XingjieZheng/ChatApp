package com.xingjiezheng.chatapp.business.message.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.message.Message;
import com.xingjiezheng.chatapp.business.message.conversation.ConversationActivity;
import com.xingjiezheng.chatapp.constants.Extras;
import com.xingjiezheng.chatapp.framework.BaseFragment;
import com.xingjiezheng.chatapp.util.SnackbarUtils;

import java.util.List;

public class MessageFragment extends BaseFragment implements MessageRecyclerViewAdapter.OnListFragmentInteractionListener, MessageContract.View {

    private RecyclerView recyclerView;
    private MessageRecyclerViewAdapter contactsRecyclerViewAdapter;
    private MessageContract.Presenter presenter;
    private Activity activity;

    private boolean isNeedRefresh;

    public MessageFragment() {
        init();
    }

    private void init() {
        isNeedRefresh = true;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        Context context = view.getContext();
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else {
            try {
                throw new Exception("Error, contacts fragment must attach to a activiyt!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        contactsRecyclerViewAdapter = new MessageRecyclerViewAdapter(this, activity);
        recyclerView.setAdapter(contactsRecyclerViewAdapter);

        presenter = new MessagePresenter(this, activity.getLoaderManager());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isNeedRefresh) {
            presenter.getContacts();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else {
            throw new RuntimeException("please pass an activity first to use this call");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onListFragmentInteraction(Message item, int position) {
        if (item != null && item.getUser() != null && item.getUser().getUserId() != null) {
            Activity activity = getActivity();
            Intent intent = new Intent(activity, ConversationActivity.class);
            intent.putExtra(Extras.EXTRA_USER_ID, item.getUser().getUserId());
            activity.startActivity(intent);
        }
    }

    @Override
    public void showMessage(String message) {
        if (recyclerView != null && message != null) {
            SnackbarUtils.show(recyclerView, message);
        }
    }

    @Override
    public void showProgress() {
        // TODO: 2016/7/8  
    }

    @Override
    public void hideProgress() {
        // TODO: 2016/7/8
    }

    @Override
    public void setData(List<Message> list) {
        if (list != null && activity != null && isAdded()) {
            contactsRecyclerViewAdapter.setData(list);
        }
    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
