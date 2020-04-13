package com.kisannetwork.ui.contacts.view.fragments;

import android.content.Intent;
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
import com.kisannetwork.model.Contact;
import com.kisannetwork.ui.contacts.adapter.ContactsAdapter;
import com.kisannetwork.ui.contacts.view.ContactDetail;
import com.kisannetwork.ui.contacts.viewmodel.ContactsViewModel;

import org.json.JSONException;
import java.io.IOException;
import java.util.List;

public class ContactsFragment extends Fragment {

    FragmentContactsBinding binding;
    ContactsViewModel contactsViewModel;
    List<Contact> contactList;
    ContactsAdapter contactsAdapter;
    public static final String CONTACT_TAG = "contact_detail";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false);
        contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        contactsViewModel.getContactMutableLiveData().observe(getViewLifecycleOwner(), this::consumeContactsResponse);

        contactsAdapter = new ContactsAdapter(getContext(), contactList);

        // Adding Line decoration for RecyclerView
        binding.messagesRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.messagesRv.setAdapter(contactsAdapter);
        binding.messagesRv.setLayoutManager(new LinearLayoutManager(getContext()));

        contactsAdapter.setOnItemClickListener(((view, position) -> {
            Intent intent = new Intent(getActivity(), ContactDetail.class);

            // Putting data in Bundle to send in ContactDetail Activity
            Bundle b = new Bundle();
            b.putParcelable(CONTACT_TAG, contactList.get(position));
            intent.putExtras(b);
            startActivity(intent);
        }));

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            contactsViewModel.getContacts();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Consuming Contacts Response from a static Contact JSON in raw via ViewModel and Respository (MVVM)
    * */
    private void consumeContactsResponse(List<Contact> contacts) {
        contactList = contacts;
        ((ContactsAdapter)binding.messagesRv.getAdapter()).notifyData(contacts);
    }
}
