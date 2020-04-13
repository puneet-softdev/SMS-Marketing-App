package com.kisannetwork.ui.contacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kisannetwork.R;
import com.kisannetwork.db.entity.MessageHistoryEntity;
import com.kisannetwork.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MessageHistoryAdapter extends RecyclerView.Adapter<MessageHistoryAdapter.ViewHolder> {

    private Context mContext;
    private List<MessageHistoryEntity> mMessagesList;

    public MessageHistoryAdapter(Context context, List<MessageHistoryEntity> mMessagesList) {
        this.mContext = context;
        this.mMessagesList = mMessagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_row,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageHistoryEntity messageHistoryEntity = mMessagesList.get(position);
        holder.contactName.setText(messageHistoryEntity.getName());
        holder.date.setText(messageHistoryEntity.getDate());
        holder.message.setText(messageHistoryEntity.getMessage());
        holder.number.setText(messageHistoryEntity.getNumber());
    }

    @Override
    public int getItemCount() {
        return mMessagesList!=null?mMessagesList.size():0;
    }

    public void notifyData(List<MessageHistoryEntity> messageHistoryEntities) {
        mMessagesList = new ArrayList<>();
        mMessagesList.clear();
        mMessagesList.addAll(messageHistoryEntities);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView contactName;
        private TextView date;
        private TextView message;
        private TextView number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message_tv);
            contactName = (TextView) itemView.findViewById(R.id.name_tv);
            date = (TextView) itemView.findViewById(R.id.date_tv);
            number = (TextView) itemView.findViewById(R.id.number_tv);
        }
    }
}
