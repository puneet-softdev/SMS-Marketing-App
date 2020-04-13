package com.kisannetwork.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "T_MESSAGE_HISTORY")
public class MessageHistoryEntity {

    public MessageHistoryEntity() {
    }

    @PrimaryKey(autoGenerate = true)
    public int mId;

    public MessageHistoryEntity(String fullName, String number, String otpText, String currentDateTime, String inputMessage) {
        this.name = fullName;
        this.number = number;
        this.otpSent= otpText;
        this.date = currentDateTime;
        this.message = inputMessage;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOtpSent() {
        return otpSent;
    }

    public void setOtpSent(String otpSent) {
        this.otpSent = otpSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ColumnInfo(name = "mFullName")
    private String name;

    @ColumnInfo(name = "mNumber")
    private String number;

    @ColumnInfo(name = "mOtpCode")
    private String otpSent;

    @ColumnInfo(name = "mMessage")
    private String message;

    @ColumnInfo(name = "mTimestamp")
    private String date;

}
