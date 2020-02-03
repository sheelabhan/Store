package com.sheela.mobilestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        sensorGyro();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();


                if (TextUtils.isEmpty(etUserName.getText())) {
                    etUserName.setError("Please enter User Name");
                    etUserName.requestFocus();
                    return;

                } else if (TextUtils.isEmpty(etPassword.getText())) {
                    etPassword.setError("Please enter Password");
                    etPassword.requestFocus();
                    return;


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
    }


    private void login() {
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        final String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();


        LoginBLL loginBLL = new LoginBLL();
        com.sheela.mobilestore.model.username Username = new username(username, password);
        StrictMode.StrictMode();
        UserAPI userapi = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> userCall = userapi.checklogin(Username);
        userCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                Notification notification = new NotificationCompat.Builder(Login.this, CreateChannel.CHANNEL_1).
                        setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("Login")
                        .setContentText("You are login successfully!!")
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManagerCompat.notify(1, notification);
                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Url.token += response.body().getToken();
                Intent intent = new Intent(Login.this, HomeActivity.class);
                startActivity(intent);


            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(Login.this, "error is =" + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

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




