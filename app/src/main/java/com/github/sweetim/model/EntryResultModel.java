package com.github.sweetim.model;

import com.google.gson.annotations.SerializedName;

public class EntryResultModel {
    @SerializedName("ok")
    public boolean ok;
    @SerializedName("result")
    public String result;

    @Override
    public String toString() {
        return String.format("[%b] %s", ok, result);
    }
}
