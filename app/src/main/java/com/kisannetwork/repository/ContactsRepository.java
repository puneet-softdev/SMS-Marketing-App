package com.kisannetwork.repository;

import com.kisannetwork.model.Contact;
import com.kisannetwork.utils.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    public List<Contact> getContactList(String contactsJson) throws JSONException {
        JSONArray jsonArray = new JSONArray(contactsJson);
        JSONObject contact;
        List<Contact> tmpContactList = new ArrayList<>(jsonArray.length());
        for(int i = 0; i < jsonArray.length(); i++) {
            contact = jsonArray.getJSONObject(i);
            final String fullName = contact.getString(AppConstants.FULL_NAME);
            final String countryCode = contact.getString(AppConstants.COUNTRY_CODE);
            final String mobileNumber = contact.getString(AppConstants.MOBILE_NUMBER);
            tmpContactList.add(new Contact(fullName, countryCode+mobileNumber));
        }
        return tmpContactList;
    }
}
