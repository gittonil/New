package com.cuelogic.myapplication.network.apis;

import com.cuelogic.myapplication.interfaces.HTTPRequestStatusListener;
import com.cuelogic.myapplication.network.networkmanager.HTTPRequestManager;

/**
 * Created by cuelogic on 31/08/15.
 */
public abstract class BaseAPIRequest extends HTTPRequestManager implements HTTPRequestStatusListener {

    ///////////////////////////////////////////////////////////////////////////
    // QA BASE URL
    ///////////////////////////////////////////////////////////////////////////
    protected final String BASE_URL = "https://qa.cuelogic.com/apis/";

    ///////////////////////////////////////////////////////////////////////////
    // DEVELOPMENT BASE URL
    ///////////////////////////////////////////////////////////////////////////
    // protected final String BASE_URL = "https://dev.cuelogic.com/apis/";

    ///////////////////////////////////////////////////////////////////////////
    // PRODUCTION BASE URL
    ///////////////////////////////////////////////////////////////////////////
    // protected final String BASE_URL = "https://prod.cuelogic.com/apis/";

    abstract String getAPIUrl();
}
