package com.cuelogic.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cuelogic.myapplication.R;

public class SplashScreenActivity extends BaseActivity {

    private final int SPLASH_TIME_OUT_INT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        showSplashScreen();
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    private void showSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT_INT);
    }
}
