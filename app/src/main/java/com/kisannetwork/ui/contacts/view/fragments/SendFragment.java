package com.kisannetwork.ui.contacts.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.kisannetwork.BaseApplication;
import com.kisannetwork.R;
import com.kisannetwork.data.ApiResponse;
import com.kisannetwork.databinding.FragmentComposeBinding;
import com.kisannetwork.db.entity.MessageHistoryEntity;
import com.kisannetwork.model.Contact;
import com.kisannetwork.model.Message;
import com.kisannetwork.ui.contacts.viewmodel.SendMessageViewModel;
import com.kisannetwork.utils.AppConstants;
import com.kisannetwork.utils.AppUtility;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SendFragment extends Fragment {
    FragmentComposeBinding binding;
    SendMessageViewModel viewModel;
    private Contact mContact;
    public static final String CONTACT_TAG = "contact_detail";
    ProgressDialog progressDialog;
    private String otpText;
    private String currentDateTime;
    private String inputMessage;

    public static SendFragment newInstance(Contact contact){
        SendFragment sendFragment = new SendFragment();
        Bundle args = new Bundle();
        args.putParcelable(CONTACT_TAG, contact);
        sendFragment.setArguments(args);
        return sendFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContact = getArguments().getParcelable(CONTACT_TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SendMessageViewModel.class);

        // Observing Livedata for TextMessage and Database Response
        viewModel.messageResponse().observe(getViewLifecycleOwner(), this::consumeResponse);
        viewModel.getResponseInsertMessage().observe(getViewLifecycleOwner(), this::consumeInsertResponse);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_compose, container, false);
        return binding.getRoot();
    }

    /*
    * Database response
    * @param response - Inserted Row Count
    * */
    private void consumeInsertResponse(Long responseValue) {
        if(responseValue<0){
            Toast.makeText(getActivity(), getResources().getString(R.string.error_db_insert), Toast.LENGTH_SHORT).show();
        }else{
            Log.d(CONTACT_TAG, getResources().getString(R.string.success_db_insert));
        }
    }

    /*
    * Consume Text Message Twilio Response
    * */
    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                Toast.makeText(getActivity(), apiResponse.status.toString(), Toast.LENGTH_SHORT).show();
                binding.messageEdt.setText("");
                // Save data in Database - Room
                MessageHistoryEntity message = new MessageHistoryEntity(mContact.getFullName(), mContact.getNumber(), otpText, currentDateTime,otpText + " " + inputMessage);
                viewModel.insertMessage(message);
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(getActivity(),getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = AppUtility.getInstance().getProgressDialog(getContext(), "Please wait...");
        binding.nameTv.setText(mContact.getFullName());
        binding.imageButton.setOnClickListener((view -> {
            if(AppUtility.isNetworkConnected(getActivity())){
                viewModel.hitTwillioApi(getSmsMap(), BaseApplication.getInstance().twillioWebService);
            }else{
                Toast.makeText(getActivity(), getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private Map<String, String> getSmsMap(){
        otpText = getResources().getString(R.string.otp_caption) + AppUtility.getInstance().getSixDigitRandomNumber();
        currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
        inputMessage = binding.messageEdt.getText().toString();

        HashMap<String, String> smsMap = new HashMap<>();
        smsMap.put("From", AppConstants.FROM_NUMBER);
        smsMap.put("To", AppConstants.TO_NUMBER);
        smsMap.put("Body", otpText + " " + inputMessage);
        return smsMap;
    }
}
