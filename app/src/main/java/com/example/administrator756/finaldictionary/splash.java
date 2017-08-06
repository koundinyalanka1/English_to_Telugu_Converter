package com.example.administrator756.finaldictionary;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import static com.example.administrator756.finaldictionary.R.layout.splashscreen;

/**
 * Created by Administrator756 on 1/23/2017.
 */

public class splash extends AppCompatActivity {

    ImageView image ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(splashscreen);
        image = (ImageView)findViewById(R.id.image) ;
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 600.0f, -50.0f);
        animation.setDuration(1700);
        animation.setRepeatCount(0);
        animation.setRepeatMode(0);
        animation.setFillAfter(true);
        image.startAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(splash.this, Home_sc.class);
                startActivity(intent);
            }
        }, 1800);

    }
}