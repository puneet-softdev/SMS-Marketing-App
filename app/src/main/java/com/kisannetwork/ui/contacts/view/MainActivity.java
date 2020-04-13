package com.kisannetwork.ui.contacts.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kisannetwork.R;
import com.kisannetwork.databinding.ActivityMainBinding;
import com.kisannetwork.ui.contacts.adapter.ViewPagerAdapter;
import com.kisannetwork.ui.contacts.view.fragments.ContactsFragment;
import com.kisannetwork.ui.contacts.view.fragments.MessagesFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    // tab titles
    private String[] titles = new String[]{"Contacts", "Messages"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        setSupportActionBar(binding.toolbar);
        setUpViewPager();
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPagerAdapter.addFragment(new ContactsFragment());
        viewPagerAdapter.addFragment(new MessagesFragment());
        binding.viewPager.setAdapter(viewPagerAdapter);
        // attaching tab mediator for setting Tab Title (ViewPager2 - Android)
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,(tab, position) -> tab.setText(titles[position])).attach();
    }
}
