package com.sheela.mobilestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class CheckConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_connection);
        checkConnection();
    }
    private void checkConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager)

                this.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        NetworkInfo network = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if (wifi.isConnected()) {

            Toast.makeText(this, "Wifi is connected ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CheckConnectionActivity.this, Login.class);
            startActivity(intent);

        } else if (network.isConnected()) {

            Intent intent = new Intent(CheckConnectionActivity.this, Login.class);
            startActivity(intent);
            Toast.makeText(this, "Internet is available ", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(this, "Internet is not available ", Toast.LENGTH_LONG).show();

        }
    }}
