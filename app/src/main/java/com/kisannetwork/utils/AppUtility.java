package com.kisannetwork.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;

import java.util.Random;

public class AppUtility {

    private static AppUtility instance = null;

    private AppUtility(){

    }

    public static AppUtility getInstance(){
        if(instance == null){
            instance = new AppUtility();
        }
        return instance;
    }

    public String getSixDigitRandomNumber() {
        Random ran = new Random();
        int num = ran.nextInt(900000) + 100000;
        return String.valueOf(num);
    }

    public static ProgressDialog getProgressDialog(Context context, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
