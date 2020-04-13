package com.kisannetwork.ui.contacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.kisannetwork.R;
import com.kisannetwork.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> mContactList;
    public OnItemClickListener mClickListener;

    public  ContactsAdapter(Context context, List<Contact> contactList) {
        this.mContext = context;
        this.mContactList = contactList;
    }

    /**
     * Interface used to create on click listener
     */
    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContactList.get(position);
        holder.contactName.setText(contact.getFullName()); // full name of contact
        holder.contactNumber.setText(contact.getNumber()); // contact number
        holder.contactImage.setImageDrawable(contact.getDrawable()); // text drawable, just like in Gmail

        holder.itemView.setOnClickListener((view -> {
            mClickListener.onClick(view, holder.getAdapterPosition());
        }));
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView contactImage; // ImageView where, text drawable will be populated
        private TextView contactName;   // TextView to show contact name
        private TextView contactNumber; // TextView to show contact number

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactImage = (ImageView) itemView.findViewById(R.id.contact_iv);
            contactName = (TextView) itemView.findViewById(R.id.name_tv);
            contactNumber = (TextView) itemView.findViewById(R.id.phone_tv);
        }
    }

    public void notifyData(List<Contact> list) {
        mContactList = new ArrayList<>();
        mContactList.clear();
        mContactList.addAll(list);
        notifyDataSetChanged();
    }
}
