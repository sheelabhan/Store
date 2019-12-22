package com.sheela.mobilestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    private Button btnAddMobiles, btnUserAccount, btnViewMobiles,btnViewMessages, btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnAddMobiles=findViewById(R.id.btnAddMobiles);
        btnUserAccount=findViewById(R.id.btnUserAccount);
        btnViewMobiles=findViewById(R.id.btnViewMobiles);
        btnViewMessages=findViewById(R.id.btnViewMessages);
        btnLogout=findViewById(R.id.btnLogout);

        btnAddMobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnUserAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DashboardActivity.this, Login.class);
                startActivity(intent);
            }
        });
        btnViewMobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnViewMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

