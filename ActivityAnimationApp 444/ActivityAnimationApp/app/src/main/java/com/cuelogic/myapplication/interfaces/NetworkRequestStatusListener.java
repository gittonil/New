package com.cuelogic.myapplication.interfaces;

import com.cuelogic.myapplication.models.outputmodels.BaseOutputData;
import com.cuelogic.myapplication.network.apis.BaseAPIRequest;
import com.cuelogic.myapplication.network.jsonparsers.NetworkErrorJsonParser;

/**
 * Created by cuelogic on 31/08/15.
 */
public interface NetworkRequestStatusListener {

    ///////////////////////////////////////////////////////////////////////////
    // Request Success Callback.
    ///////////////////////////////////////////////////////////////////////////
    public void onRequestSuccess(BaseAPIRequest baseAPIRequest, BaseOutputData baseOutputData);

    ///////////////////////////////////////////////////////////////////////////
    // Request Failure Callback.
    ///////////////////////////////////////////////////////////////////////////
    public void onRequestFaliure(BaseAPIRequest baseAPIRequest, NetworkErrorJsonParser networkErrorJsonParser);

}
