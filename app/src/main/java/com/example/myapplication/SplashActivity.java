package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends Activity
{
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        try
        {
            Thread.sleep(1500); //대기초 설정

        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        startActivity(new Intent(this, StartActivity.class));
        finish();
        
    }

}
