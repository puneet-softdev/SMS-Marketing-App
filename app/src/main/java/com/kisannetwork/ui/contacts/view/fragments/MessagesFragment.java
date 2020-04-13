package com.kisannetwork.ui.contacts.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kisannetwork.R;
import com.kisannetwork.databinding.FragmentContactsBinding;
import com.kisannetwork.db.entity.MessageHistoryEntity;
import com.kisannetwork.model.Contact;
import com.kisannetwork.ui.contacts.adapter.ContactsAdapter;
import com.kisannetwork.ui.contacts.adapter.MessageHistoryAdapter;
import com.kisannetwork.ui.contacts.viewmodel.MessageHistoryViewModel;

import java.util.List;

public class MessagesFragment extends Fragment {

    private FragmentContactsBinding fragmentContactsBinding;
    private MessageHistoryViewModel messageHistoryViewModel;

    List<MessageHistoryEntity> messagesList;
    MessageHistoryAdapter messageHistoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        messageHistoryViewModel = new ViewModelProvider(this).get(MessageHistoryViewModel.class);
        messageHistoryViewModel.getMessagesLiveData().observe(getViewLifecycleOwner(), this::consumeResponse);
        fragmentContactsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false);

        messageHistoryAdapter = new MessageHistoryAdapter(getContext(), messagesList);
        //fragmentContactsBinding.messagesRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        fragmentContactsBinding.messagesRv.setAdapter(messageHistoryAdapter);
        fragmentContactsBinding.messagesRv.setLayoutManager(new LinearLayoutManager(getContext()));

        return fragmentContactsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        messageHistoryViewModel.getMessageHistory();
    }

    private void consumeResponse(List<MessageHistoryEntity> messageHistoryEntities) {
        messagesList = messageHistoryEntities;
        ((MessageHistoryAdapter)fragmentContactsBinding.messagesRv.getAdapter()).notifyData(messageHistoryEntities);
    }
}
