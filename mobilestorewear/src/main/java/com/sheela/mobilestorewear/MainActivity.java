package com.sheela.mobilestorewear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sheela.mobilestorewear.StrictModeClass.StrictMode;
import com.sheela.mobilestorewear.api.UserAPI;
import com.sheela.mobilestorewear.model.username;
import com.sheela.mobilestorewear.serverresponse.SignUpResponse;
import com.sheela.mobilestorewear.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends WearableActivity {

    private FrameLayout frameLayout;
    private TextView tvusername, tvpassword;
    private EditText etuname, etpass;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etuname=findViewById(R.id.etuname);
        etpass=findViewById(R.id.etpass);
        btnlogin=findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        String username= etuname.getText().toString();
        String password= etpass.getText().toString();
        com.sheela.mobilestorewear.model.username username1= new username(username,password);
        StrictMode.StrictMode();
        UserAPI userAPI= Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> usercall= userAPI.checklogin(username1);
        usercall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    etuname.setText("");
                    etpass.setText("");

                    return;
                }

                Toast.makeText(MainActivity.this, "Redirecting.....", Toast.LENGTH_SHORT).show();
                Url.token += response.body().getToken();
                Toast.makeText(MainActivity.this, "Login succesfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    }

