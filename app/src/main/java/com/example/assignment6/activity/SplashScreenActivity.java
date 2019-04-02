package com.example.assignment6.activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.assignment6.R;
import com.example.assignment6.util.CheckInternet;

import static com.example.assignment6.util.Constant.NO_INTERNET_TOAST;
import static com.example.assignment6.util.Constant.SPLASH_TIME;

public class SplashScreenActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash_screen);

        boolean checkConnection = new CheckInternet().checkConnection(this);
        if(checkConnection){
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },  SPLASH_TIME);
        }
        else
            Toast.makeText(this,NO_INTERNET_TOAST,Toast.LENGTH_SHORT).show();
    }
}

