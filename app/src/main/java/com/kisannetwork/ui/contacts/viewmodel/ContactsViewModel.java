package com.kisannetwork.ui.contacts.viewmodel;

import android.app.Application;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kisannetwork.R;
import com.kisannetwork.model.Contact;
import com.kisannetwork.repository.ContactsRepository;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Contact>> contactMutableLiveData = new MutableLiveData<>();

    public ContactsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Contact>> getContactMutableLiveData() {
        return contactMutableLiveData;
    }

    public void getContacts() throws JSONException, IOException {
        ContactsRepository contactsRepository = new ContactsRepository();
        contactMutableLiveData.setValue(contactsRepository.getContactList(readContactsFromResources()));
    }

    private String readContactsFromResources() throws IOException {
        StringBuilder statesJson = new StringBuilder();
        Resources resources = getApplication().getResources();
        InputStream rawStates = resources.openRawResource(R.raw.contact);
        BufferedReader reader = new BufferedReader(new InputStreamReader(rawStates));
        String line;
        while ((line = reader.readLine()) != null) {
            statesJson.append(line);
        }
        return statesJson.toString();
    }
}
