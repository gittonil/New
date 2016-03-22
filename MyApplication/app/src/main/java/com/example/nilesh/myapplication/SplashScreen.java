package com.example.nilesh.myapplication;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends MainActivity {

    private int Splash_TimeOut = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new android.os.Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, Splash_TimeOut);



    }

}
