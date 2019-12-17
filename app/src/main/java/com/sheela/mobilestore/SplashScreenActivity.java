package com.sheela.mobilestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // imgLogo= findViewById(R.id.imgLogo);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                String password = sharedPreferences.getString("password", "");
                if (username.equals("admin") &&
                        password.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Either Username and Password is incorrect",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SplashScreenActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }

            }

        }, 2000);
    }
}

