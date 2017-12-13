package com.huzaif.admi.demo;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Splash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=21)
        {  setTheme(R.style.AppTheme_NoActionBar);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        }
        else
        {
            setTheme(R.style.AppTheme);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent i = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(i);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}


