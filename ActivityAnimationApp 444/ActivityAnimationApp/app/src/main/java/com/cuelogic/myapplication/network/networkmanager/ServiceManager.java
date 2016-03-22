package com.cuelogic.myapplication.network.networkmanager;

import com.cuelogic.myapplication.interfaces.FragmentResponseReceiverListener;
import com.cuelogic.myapplication.interfaces.MyApplicationAPIs;
import com.cuelogic.myapplication.interfaces.NetworkRequestStatusListener;
import com.cuelogic.myapplication.models.inputmodels.LoginUserInputData;
import com.cuelogic.myapplication.models.outputmodels.BaseOutputData;
import com.cuelogic.myapplication.network.apis.BaseAPIRequest;
import com.cuelogic.myapplication.network.apis.LoginUserAPIRequest;
import com.cuelogic.myapplication.network.jsonparsers.NetworkErrorJsonParser;

import java.util.HashMap;

/**
 * Created by cuelogic on 31/08/15.
 */
public class ServiceManager implements MyApplicationAPIs, NetworkRequestStatusListener {

    private static ServiceManager serviceManagerInstance = null;

    private LoginUserAPIRequest loginUserAPIRequest;

    private HashMap<BaseAPIRequest, FragmentResponseReceiverListener> callbackHashMap;

    private ServiceManager() {
        callbackHashMap = new HashMap<BaseAPIRequest, FragmentResponseReceiverListener>();
    }

    /**
     * This method returns the ServiceManager instance.
     */
    public static ServiceManager getInstance() {
        if(serviceManagerInstance == null) {
            serviceManagerInstance = new ServiceManager();
        }
        return serviceManagerInstance;
    }

    @Override
    public void onRequestSuccess(BaseAPIRequest baseAPIRequest, BaseOutputData baseOutputData) {
        FragmentResponseReceiverListener listener = callbackHashMap.get(baseAPIRequest);
        if (listener !=null) {
            listener.onSuccessResponseReceived(baseOutputData);
            callbackHashMap.remove(baseAPIRequest);
        }
    }

    @Override
    public void onRequestFaliure(BaseAPIRequest baseAPIRequest, NetworkErrorJsonParser networkErrorJsonParser) {
        FragmentResponseReceiverListener listener = callbackHashMap.get(baseAPIRequest);
        if (listener !=null) {
            listener.onFaliureResponseReceived(networkErrorJsonParser);
            callbackHashMap.remove(baseAPIRequest);
        }
    }

    @Override
    public void loginUser(FragmentResponseReceiverListener fragmentResponseReceiverListener,
                          LoginUserInputData loginUserInputData) {
        loginUserAPIRequest = new LoginUserAPIRequest(this, loginUserInputData);
        callbackHashMap.put(loginUserAPIRequest, fragmentResponseReceiverListener);
        loginUserAPIRequest.sendAPIRequest(loginUserAPIRequest);
    }
}
