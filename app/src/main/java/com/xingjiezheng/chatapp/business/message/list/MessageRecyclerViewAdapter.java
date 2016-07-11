package com.xingjiezheng.chatapp.business.message.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.message.Message;
import com.xingjiezheng.chatapp.glide.GlideCircleTransform;
import com.xingjiezheng.chatapp.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder> {

    private List<Message> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public MessageRecyclerViewAdapter(OnListFragmentInteractionListener listener, Context context) {
        mListener = listener;
        this.context = context;
        mValues = new ArrayList<>(0);
    }

    public void setData(List<Message> list) {
        mValues = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Glide.with(context)
                .load(mValues.get(position).getUser().getAvatar())
                .transform(new GlideCircleTransform(context))
                .into(holder.imgAvatar);
        holder.txtName.setText(mValues.get(position).getUser().getUserName());
        holder.txtContent.setText(mValues.get(position).getContent());
        holder.txtTime.setText(TimeUtils.getDateByTimeMillis(mValues.get(position).getTime()));
        holder.txtUnReadCount.setText(String.valueOf(mValues.get(position).getUnReadCount()));

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
        public final TextView txtUnReadCount;
        public Message mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtContent = (TextView) view.findViewById(R.id.txtContent);
            txtTime = (TextView) view.findViewById(R.id.txtTime);
            txtUnReadCount = (TextView) view.findViewById(R.id.txtUnReadCount);
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
