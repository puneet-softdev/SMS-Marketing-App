package com.kisannetwork.repository;

import android.util.Base64;
import android.util.Log;

import com.kisannetwork.BaseApplication;
import com.kisannetwork.data.TwillioWebService;
import com.kisannetwork.utils.AppConstants;

import java.util.Map;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.kisannetwork.utils.AppConstants.ACCOUNT_SID;
import static com.kisannetwork.utils.AppConstants.AUTH_TOKEN;

public class SendMessageRepository {

    private TwillioWebService twillioWebService;

    public SendMessageRepository(TwillioWebService twillioWebService){
        this.twillioWebService = twillioWebService;
    }

    public Single<ResponseBody> twillioCall(Map<String, String> smsData){
        String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP
        );

        Single<ResponseBody> call = BaseApplication.getInstance().twillioWebService.sendMessage(ACCOUNT_SID, base64EncodedCredentials, smsData)
                .subscribeOn(Schedulers.io());
        return call;
    }


}
