package com.sheela.mobilestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.api.UserAPI;
import com.sheela.mobilestore.bll.LoginBLL;
import com.sheela.mobilestore.model.username;
import com.sheela.mobilestore.serverresponse.SignUpResponse;
import com.sheela.mobilestore.ui.home.HomeFragment;
import com.sheela.mobilestore.url.Url;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText etUserName, etPassword;
    private Button btnLogin;
    private TextView txtRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        txtRegister = findViewById(R.id.txtRegister);
        btnLogin = findViewById(R.id.btnLogin);
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
        String username = etUserName.getText().toString();
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
}




