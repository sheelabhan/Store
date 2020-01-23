package com.sheela.mobilestore;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sheela.mobilestore.model.BroadcasteRecieverExample;

public class BroadcastActivity extends AppCompatActivity {
    BroadcasteRecieverExample broadcasteRecieverExample= new BroadcasteRecieverExample();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadast);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter= new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcasteRecieverExample,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcasteRecieverExample);
    }
}
