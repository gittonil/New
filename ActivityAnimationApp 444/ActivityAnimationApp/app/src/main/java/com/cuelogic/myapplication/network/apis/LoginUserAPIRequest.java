package com.cuelogic.myapplication.network.apis;

import com.cuelogic.myapplication.interfaces.NetworkRequestStatusListener;
import com.cuelogic.myapplication.models.inputmodels.LoginUserInputData;
import com.cuelogic.myapplication.network.jsonparsers.LoginUserJsonParser;
import com.cuelogic.myapplication.network.jsonparsers.NetworkErrorJsonParser;
import com.cuelogic.myapplication.network.jsonwriters.LoginUserJsonWriter;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by cuelogic on 31/08/15.
 */
public class LoginUserAPIRequest extends BaseAPIRequest {

    private NetworkRequestStatusListener networkRequestStatusListener;
    private BaseAPIRequest baseAPIRequest;
    private LoginUserInputData loginUserInputData;

    private LoginUserJsonWriter loginUserJsonWriter;

    public LoginUserAPIRequest(NetworkRequestStatusListener networkRequestStatusListener,
                               LoginUserInputData loginUserInputData) {
        this.loginUserInputData = loginUserInputData;
        this.networkRequestStatusListener = networkRequestStatusListener;
    }

    public void sendAPIRequest(BaseAPIRequest baseAPIRequest) {
        this.baseAPIRequest = baseAPIRequest;

        loginUserJsonWriter = new LoginUserJsonWriter(loginUserInputData);
        JSONObject loginJsonObject = loginUserJsonWriter.createJSON();
        sendGETRequest(getAPIUrl(), this);
    }

    @Override
    String getAPIUrl() {
        String apiUrl = BASE_URL+"login.json";
        return apiUrl;
    }

    @Override
    public void onHTTPResponseSuccess(int statusCode, Header[] headerInfo, byte[] responseData) {
        LoginUserJsonParser loginUserJsonParser = new LoginUserJsonParser(responseData);
        networkRequestStatusListener.onRequestSuccess(baseAPIRequest, loginUserJsonParser.parseJson() );
    }

    @Override
    public void onHTTPResponseFailure(int statusCode, Header[] headerInfo, byte[] responseData, Throwable throwable) {
        if (responseData != null) {
            NetworkErrorJsonParser networkErrorJsonParser = new NetworkErrorJsonParser(responseData);
            networkRequestStatusListener.onRequestFaliure(baseAPIRequest, networkErrorJsonParser);
        }
    }
}
