package com.kisannetwork.ui.contacts.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import com.kisannetwork.R;
import com.kisannetwork.databinding.ActivityDetailContactBinding;
import com.kisannetwork.model.Contact;
import com.kisannetwork.ui.contacts.view.fragments.ContactDetailsFragment;
import com.kisannetwork.ui.contacts.view.fragments.SendFragment;

public class ContactDetail extends AppCompatActivity {
    ActivityDetailContactBinding binding;
    Contact contact;
    public static final String CONTACT_TAG = "contact_detail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_contact);
        setSupportActionBar(binding.toolbar);
        extractContactData();

        /*
        * Adding fragment here for future purpose if Need to show two fragments (Contact Detail Screen and Send Message Screen)
        * in Tablet View
        * Meanwhile showing both fragments as per mobile view, next to each other
         * */
        addContactDetailFragment();
    }

    private void extractContactData(){
        Bundle b = getIntent().getExtras();
        contact = b.getParcelable(CONTACT_TAG);
    }

    private void addContactDetailFragment(){
        getSupportFragmentManager().beginTransaction().add(binding.frameContainer.getId(), ContactDetailsFragment.newInstance(contact)).commit();
    }

    // Replacing Send Fragment on clicking of Send Message Button in Currently attached fragment
    public void show(Contact mContact) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(binding.frameContainer.getId(), SendFragment.newInstance(mContact)).commit();
    }
}
