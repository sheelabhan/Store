package com.sheela.mobilestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.ResourceBundle;

public class Login extends AppCompatActivity {
    private EditText etUserName, etPassword;
    private Button btnLogin;
    private TextView txtRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName=findViewById(R.id.etUserName);
        etPassword=findViewById(R.id.etPassword);
        txtRegister=findViewById(R.id.txtRegister);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                saveData();
                if (TextUtils.isEmpty(etUserName.getText())) {
                    etUserName.setError("Please enter User Name");
                    etUserName.requestFocus();
                    return;

                } else if (TextUtils.isEmpty(etPassword.getText())) {
                    etPassword.setError("Please enter Password");
                    etPassword.requestFocus();
                    return;

                }
                String Username, Password;
                Username = etUserName.getText().toString();
                Password = etPassword.getText().toString();


                if (Objects.equals(Username, "admin") && Objects.equals(Password, "admin")) {
//                   // Toast.makeText(getApplicationContext(), "redirecting...", Toast.LENGTH_SHORT).show();
                }
            }


        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent SignUpintent= new Intent(Login.this, SignUp.class);
                startActivity(SignUpintent);

            }
        });
    }

    private void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences("username",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username", etUserName.getText().toString());
        editor.putString("password",etPassword.getText().toString());
        editor.commit();


    }
}



