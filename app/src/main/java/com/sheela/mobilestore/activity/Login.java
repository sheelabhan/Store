package com.sheela.mobilestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.api.UserAPI;
import com.sheela.mobilestore.bll.LoginBLL;
import com.sheela.mobilestore.createchannel.CreateChannel;
import com.sheela.mobilestore.model.username;
import com.sheela.mobilestore.serverresponse.SignUpResponse;
import com.sheela.mobilestore.url.Url;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText etUserName, etPassword;
    private Button btnLogin;
    private TextView txtRegister;
    private ImageView insta,facebook;
    private NotificationManagerCompat notificationManagerCompat;
    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        txtRegister = findViewById(R.id.txtRegister);
        btnLogin = findViewById(R.id.btnLogin);
        facebook=findViewById(R.id.facebook);

        insta=findViewById(R.id.insta);

        sensorGyro();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (TextUtils.isEmpty(etUserName.getText())) {
                    etUserName.setError("Please enter User Name");
                    etUserName.requestFocus();
                    return;

                } else if (TextUtils.isEmpty(etPassword.getText())) {
                    etPassword.setError("Please enter Password");
                    // Get instance of Vibrator from current Context
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

// Vibrate for 400 milliseconds
                    vibrator.vibrate(400);
                    etPassword.requestFocus();
                    return;


                } else{
                    login();
                }

            }


        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent SignUpintent = new Intent(Login.this, SignUp.class);
                startActivity(SignUpintent);

            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://www.facebook.com");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://www.insta.com");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }


    private void login() {
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        final String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        LoginBLL loginBLL = new LoginBLL();
        StrictMode.StrictMode();
        if(loginBLL.checklogin(username,password)) {


            Notification notification = new NotificationCompat.Builder(Login.this, CreateChannel.CHANNEL_1).
                    setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setContentTitle("Login")
                    .setContentText(username + " You are login successfully!!")
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();
            notificationManagerCompat.notify(1, notification);
            Intent intent = new Intent(Login.this, HomeActivity.class);
            intent.putExtra("Userlogin",databaseList());
            startActivity(intent);
            finish();

        }else{
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etUserName.requestFocus();
        }
//


    }

    private void sensorGyro() {

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (event.values[1] < 0) {
                    login();
                    finish();

                } else if (event.values[1] > 0) {
                    startActivity(new Intent(Login.this,SignUp.class));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }

}




