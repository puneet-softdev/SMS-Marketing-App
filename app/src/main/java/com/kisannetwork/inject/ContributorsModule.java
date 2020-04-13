package com.kisannetwork.inject;

import com.kisannetwork.ui.contacts.view.MainActivity;
import com.kisannetwork.ui.contacts.view.fragments.SendFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ContributorsModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract SendFragment sendFragment();
}
