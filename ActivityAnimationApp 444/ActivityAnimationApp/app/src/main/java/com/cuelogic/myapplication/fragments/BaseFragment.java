package com.cuelogic.myapplication.fragments;

import android.support.v4.app.Fragment;

import com.cuelogic.myapplication.interfaces.FragmentResponseReceiverListener;
import com.cuelogic.myapplication.models.outputmodels.BaseOutputData;
import com.cuelogic.myapplication.network.jsonparsers.NetworkErrorJsonParser;

/**
 * Created by cuelogic on 31/08/15.
 */
public class BaseFragment extends Fragment implements FragmentResponseReceiverListener {

    protected void initViews() {}

    @Override
    public void onSuccessResponseReceived(BaseOutputData baseOutputData) {}

    @Override
    public void onFaliureResponseReceived(NetworkErrorJsonParser networkErrorJsonParser) {}
}
