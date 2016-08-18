package com.xingjiezheng.chatapp.business.message.conversation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.Global;
import com.xingjiezheng.chatapp.business.account.User;
import com.xingjiezheng.chatapp.business.message.Message;
import com.xingjiezheng.chatapp.glide.GlideCircleTransform;
import com.xingjiezheng.chatapp.util.TimeUtils;

import java.util.List;

public class ConversationRecyclerViewAdapter extends RecyclerView.Adapter<ConversationRecyclerViewAdapter.ViewHolder> {

    private List<Message> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    private static final int TYPE_SYSTEM = 0;
    private static final int TYPE_MYSELF = 1;
    private static final int TYPE_THE_OTHER = 2;


    public ConversationRecyclerViewAdapter(OnListFragmentInteractionListener listener, Context context, List<Message> list) {
        mListener = listener;
        this.context = context;
        mValues = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_MYSELF: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_conversation_item_myself, parent, false);
            }
            break;
            case TYPE_THE_OTHER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_conversation_item_the_other, parent, false);
            }
            break;
            case TYPE_SYSTEM: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_conversation_item_system, parent, false);
            }
            break;
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_conversation_item_system, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (mValues == null || mValues.size() == 0) {
            return TYPE_SYSTEM;
        } else {
            User user = mValues.get(position).getSender();
            if (user != null && user.isIdValid() && Global.loginAccount != null) {
                if (user.getId() == (Global.loginAccount.getUserId())) {
                    return TYPE_MYSELF;
                } else {
                    return TYPE_THE_OTHER;
                }
            } else {
                return TYPE_SYSTEM;
            }
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        if (holder.imgAvatar != null) {
            Glide.with(context)
                    .load(mValues.get(position).getSender().getAvatar())
                    .transform(new GlideCircleTransform(context))
                    .into(holder.imgAvatar);
        }
        holder.txtName.setText(mValues.get(position).getSender().getNickName());
        holder.txtContent.setText(mValues.get(position).getContent());
        holder.txtTime.setText(mValues.get(position).getTime() == null ? null : mValues.get(position).getTime().toString());

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
        public final TextView txtContent;
        public final TextView txtTime;
        public Message mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtContent = (TextView) view.findViewById(R.id.txtContent);
            txtTime = (TextView) view.findViewById(R.id.txtTime);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtName.getText() + "'";
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Message item, int position);
    }
}
