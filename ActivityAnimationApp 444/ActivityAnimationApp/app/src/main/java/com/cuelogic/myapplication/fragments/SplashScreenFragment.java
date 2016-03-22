package com.cuelogic.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuelogic.myapplication.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SplashScreenFragment extends BaseFragment {

    public SplashScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }
}
