package com.sheela.mobilestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sheela.mobilestore.ProgressBarAnimation;
import com.sheela.mobilestore.R;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView imgLogo;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progressbar);
//        textView=findViewById(R.id.percentage);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAmimation();
        // imgLogo= findViewById(R.id.imgLogo);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//
//                Intent intent = new Intent(SplashScreenActivity.this, CheckConnectionActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 1000);

    }
    private void progressAmimation() {

        ProgressBarAnimation progressBarAnimation=new ProgressBarAnimation(this,progressBar,0f,100f);
        progressBarAnimation.setDuration(3000);
        progressBar.setAnimation(progressBarAnimation);
    }
}



