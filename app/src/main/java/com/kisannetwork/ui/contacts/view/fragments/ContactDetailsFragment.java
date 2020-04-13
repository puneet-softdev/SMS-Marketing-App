package com.kisannetwork.ui.contacts.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.kisannetwork.R;
import com.kisannetwork.databinding.FragmentContactDetailBinding;
import com.kisannetwork.model.Contact;
import com.kisannetwork.ui.contacts.view.ContactDetail;

public class ContactDetailsFragment extends Fragment {

    FragmentContactDetailBinding binding;
    private Contact mContact;
    public static final String CONTACT_TAG = "contact_detail";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = getArguments().getParcelable(CONTACT_TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.nameDetailTv.setText(mContact.getFullName());
        binding.phoneDetailTv.setText(mContact.getNumber());

        binding.nextBtn.setOnClickListener((view -> {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((ContactDetail) requireActivity()).show(mContact);
            }
        }));


    }

    public static ContactDetailsFragment newInstance(Contact contact){
        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(CONTACT_TAG, contact);
        contactDetailsFragment.setArguments(args);
        return contactDetailsFragment;
    }
}
