package com.xingjiezheng.chatapp.business.contacts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.business.contacts.ContactsRecyclerViewAdapter.OnListFragmentInteractionListener;
import com.xingjiezheng.chatapp.util.SnackbarUtils;

import java.util.List;

public class ContactsFragment extends Fragment implements OnListFragmentInteractionListener, ContactsContract.View {

    private RecyclerView recyclerView;
    private ContactsRecyclerViewAdapter contactsRecyclerViewAdapter;
    private ContactsContract.Presenter presenter;
    private Activity activity;

    private boolean isNeedRefresh;

    public ContactsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

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
        contactsRecyclerViewAdapter = new ContactsRecyclerViewAdapter(this, activity);
        recyclerView.setAdapter(contactsRecyclerViewAdapter);

        presenter = new ContactsPresenter(this, activity.getLoaderManager());
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
            try {
                throw new Exception("Error, contacts fragment must attach to a activiyt!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onListFragmentInteraction(User item, int position) {
        showMessage("select item (" + position + ") " + item.getUserName());
        // TODO: 2016/7/8
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
    public void setData(List<User> userList) {
        if (userList != null && activity != null && isAdded()) {
            contactsRecyclerViewAdapter.setData(userList);
        }
    }

    @Override
    public void setPresenter(ContactsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
