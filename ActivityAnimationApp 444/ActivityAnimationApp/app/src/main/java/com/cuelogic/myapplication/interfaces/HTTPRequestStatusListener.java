package com.cuelogic.myapplication.interfaces;

import org.apache.http.Header;

/**
 * Created by cuelogic on 31/08/15.
 */
public interface HTTPRequestStatusListener {

    ///////////////////////////////////////////////////////////////////////////
    // onHTTPResponseSuccess Callback.
    ///////////////////////////////////////////////////////////////////////////
    public void onHTTPResponseSuccess(int statusCode, Header[] headerInfo, byte[] responseData);

    ///////////////////////////////////////////////////////////////////////////
    // onHTTPResponseFailure Callback.
    ///////////////////////////////////////////////////////////////////////////
    public void onHTTPResponseFailure(int statusCode, Header[] headerInfo, byte[] responseData, Throwable throwable);

}
