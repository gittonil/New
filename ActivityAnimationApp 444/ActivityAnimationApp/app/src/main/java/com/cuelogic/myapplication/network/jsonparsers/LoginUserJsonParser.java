package com.cuelogic.myapplication.network.jsonparsers;

import com.cuelogic.myapplication.models.outputmodels.LoginUserOutputData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by cuelogic on 31/08/15.
 */
public class LoginUserJsonParser extends BaseJsonParser {

    private LoginUserOutputData loginUserOutputData;
    private String reponseString;

    private ArrayList<JSONArray> jsonArrays = new ArrayList<JSONArray>();

    public LoginUserJsonParser(byte[] responseData) {
        loginUserOutputData = new LoginUserOutputData();
        this.reponseString = new String(responseData);
    }

    public LoginUserOutputData parseJson() {
        try {
            JSONObject jsonObject = new JSONObject(reponseString);
            Iterator<String> iteratorKeys = jsonObject.keys();
            while (iteratorKeys.hasNext()) {
                JSONArray jsonArray = jsonObject.optJSONArray(iteratorKeys.next());
                jsonArrays.add(jsonArray);
            }
            loginUserOutputData.setJsonArrays(jsonArrays);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginUserOutputData;
    }
}
