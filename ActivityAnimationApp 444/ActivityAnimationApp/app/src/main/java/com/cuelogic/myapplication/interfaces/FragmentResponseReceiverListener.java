package com.cuelogic.myapplication.interfaces;

import com.cuelogic.myapplication.models.outputmodels.BaseOutputData;
import com.cuelogic.myapplication.network.jsonparsers.NetworkErrorJsonParser;

/**
 * Created by cuelogic on 31/08/15.
 */
public interface FragmentResponseReceiverListener {

    ///////////////////////////////////////////////////////////////////////////
    // onSuccessResponseReceived Callback.
    ///////////////////////////////////////////////////////////////////////////
    public void onSuccessResponseReceived(BaseOutputData baseOutputData);

    ///////////////////////////////////////////////////////////////////////////
    // onFaliureResponseReceived Callback.
    ///////////////////////////////////////////////////////////////////////////
    public void onFaliureResponseReceived(NetworkErrorJsonParser networkErrorJsonParser);
}
