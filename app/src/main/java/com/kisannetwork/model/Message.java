package com.kisannetwork.model;

public class Message {
    private String mFullName;  // full name of the contact

    public String getmFullName() {
        return mFullName;
    }

    public String getmNumber() {
        return mNumber;
    }

    private String mNumber; // contact associated with message
    private String mOtpCode;  // otp code that has been sent on number
    private String mTimestamp; // timestamp at which message has been sent
    private String mMessage;


    public Message(String mFullName, String mNumber, String otpCode, String timestamp, String message) {
        this.mFullName = mFullName;
        this.mNumber = mNumber;
        this.mOtpCode = otpCode;
        this.mTimestamp = timestamp;
        this.mMessage = message;
    }
    public String getTimestamp() {
        return mTimestamp;
    }

    public String getOtpCode() {
        return mOtpCode;
    }


    public String getmMessage() {
        return mMessage;
    }
}
