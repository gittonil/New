package com.cuelogic.myapplication.network.jsonwriters;

import com.cuelogic.myapplication.models.inputmodels.LoginUserInputData;

import org.json.JSONObject;

/**
 * Created by cuelogic on 31/08/15.
 */
public class LoginUserJsonWriter extends BaseJsonWriter {

    private LoginUserInputData loginUserInputData;

    private final String USERNAME = "username";
    private final String PASSWORD = "password";

    public LoginUserJsonWriter(LoginUserInputData loginUserInputData) {
        this.loginUserInputData = loginUserInputData;
    }

    public JSONObject createJSON() {
        JSONObject jsonObject = new JSONObject();
        try {

        } catch (Exception exception) {

        }
        return jsonObject;
    }
}
