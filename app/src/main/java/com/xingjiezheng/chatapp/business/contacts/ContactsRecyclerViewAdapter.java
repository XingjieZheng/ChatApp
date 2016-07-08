package com.xingjiezheng.chatapp.business.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.glide.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {

    private List<User> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public ContactsRecyclerViewAdapter(OnListFragmentInteractionListener listener, Context context) {
        mListener = listener;
        this.context = context;
        mValues = new ArrayList<>(0);
    }

    public void setData(List<User> userList) {
        mValues = userList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contacts_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Glide.with(context)
                .load(mValues.get(position).getAvatar())
                .transform(new GlideCircleTransform(context))
                .into(holder.imgAvatar);
        holder.txtName.setText(mValues.get(position).getUserName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imgAvatar;
        public final TextView txtName;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
            txtName = (TextView) view.findViewById(R.id.txtName);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtName.getText() + "'";
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(User item, int position);
    }
}
