package com.github.sweetim.model;

import android.telephony.SmsMessage;

import com.google.gson.annotations.SerializedName;

public class EntryModel {
    @SerializedName("content")
    public String content;
    @SerializedName("timestamp")
    public long timestamp;
    
    public static EntryModel createFrom(SmsMessage sms) {
        EntryModel output = new EntryModel();

        output.content = sms.getDisplayMessageBody();
        output.timestamp = sms.getTimestampMillis();
        
        return output;
    }
}
