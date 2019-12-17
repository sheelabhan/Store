package com.sheela.mobilestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private EditText etFullName, etUserName, etEmail, etPassword,etConfirmPassword,etPhoneNo;

    private Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etFullName = findViewById(R.id.etFullName);
        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etPhoneNo=findViewById(R.id.etPhoneNo);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etFullName.getText()))
                {
                    etFullName.setError("Please enter Full Name");
                    etFullName.requestFocus();
                    return;

                }
                else  if(TextUtils.isEmpty(etUserName.getText())) {
                    etUserName.setError("Please enter User Name");
                    etUserName.requestFocus();
                    return;

                }






                Intent Loginintent = new Intent(SignUp.this, Login.class);
                startActivity(Loginintent);
            }
        });


    }
}
