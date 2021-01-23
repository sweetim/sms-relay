package com.github.sweetim;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.sweetim.model.EntryModel;
import com.github.sweetim.model.EntryResultModel;
import com.github.sweetim.volley_request.GsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class SMSBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = SMSBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        ArrayList<SmsMessage> receiveSms = parseBundle(bundle);

        for (SmsMessage sms : receiveSms) {
            forwardSms(context, sms);
        }
    }

    private void forwardSms(Context context, SmsMessage sms) {
        RequestQueue queue = Volley.newRequestQueue(context);

        GsonRequest<EntryResultModel> entryRequest = new GsonRequest<>(
            Request.Method.POST,
            context.getString(R.string.forward_endpoint_url),
            EntryModel.createFrom(sms),
            EntryResultModel.class,
            new Response.Listener<EntryResultModel>() {
                @Override
                public void onResponse(EntryResultModel response) {
                    Log.i(TAG, response.toString());
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

        queue.add(entryRequest);
    }

    private ArrayList<SmsMessage> parseBundle(Bundle bundle) {
        ArrayList<SmsMessage> output = new ArrayList<>();

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[])bundle.get("pdus");
                if (pdusObj != null) {
                    for (Object obj : pdusObj) {
                        output.add(SmsMessage.createFromPdu((byte[]) obj));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }
}