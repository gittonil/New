package com.cuelogic.myapplication.models.outputmodels;

import android.support.annotation.NonNull;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by cuelogic on 31/08/15.
 */
public class LoginUserOutputData extends BaseOutputData {

    private String responseMessage;
    private ArrayList<JSONArray> jsonArrays;

    @NonNull
    public String getResponseMessage() {
        return responseMessage;
    }

    @NonNull
    public void setResponseMessage(@NonNull String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @NonNull
    public ArrayList<JSONArray> getJsonArrays() {
        return jsonArrays;
    }

    @NonNull
    public void setJsonArrays(@NonNull ArrayList<JSONArray> jsonArrays) {
        this.jsonArrays = jsonArrays;
    }
}
