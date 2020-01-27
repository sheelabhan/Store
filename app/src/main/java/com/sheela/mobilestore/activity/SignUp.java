package com.sheela.mobilestore.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.api.UserAPI;
import com.sheela.mobilestore.model.User;
import com.sheela.mobilestore.serverresponse.ImageResponse;
import com.sheela.mobilestore.serverresponse.SignUpResponse;
import com.sheela.mobilestore.url.Url;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private EditText etfirstName, etlastName, etUserName, etPassword, etConfirmPassword, etPhoneNo;
    CircleImageView imgsheela;
    String imagePath;
    private String imageName = "";
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etfirstName = findViewById(R.id.etfirstName);
        etUserName = findViewById(R.id.etUserName);
        etlastName = findViewById(R.id.etlastName);
        etPassword = findViewById(R.id.etPassword);
        imgsheela = findViewById(R.id.imgsheela);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        btnRegister = findViewById(R.id.btnRegister);
        imgsheela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(etfirstName.getText()))
                {
                    etfirstName.setError("Please enter Full Name");
                    etfirstName.requestFocus();
                    return;

                }
                else if(TextUtils.isEmpty(etlastName.getText())) {
                    etlastName.setError("Please enter User Name");
                    etlastName.requestFocus();
                    return;
                }
                else  if(TextUtils.isEmpty(etUserName.getText())) {
                    etUserName.setError("Please enter User Name");
                    etUserName.requestFocus();
                    return;

           }
                if (etPassword.getText().toString().equals((etPassword.getText().toString()))) {
                    saveImageOnly();
                    signup();
                } else {
                    Toast.makeText(SignUp.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgsheela.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile",
                file.getName(), requestBody);

        UserAPI usersAPI = Url.getInstance().create(UserAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictMode.StrictMode();
        //Synchronous method
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private void signup() {

        String firstname = etfirstName.getText().toString();
        String lastname = etlastName.getText().toString();
        String username = etUserName.getText().toString();
//        String email = etEmail.getText().toString();
        String phoneno = etPhoneNo.getText().toString();
        String password = etPassword.getText().toString();


        User user = new User(firstname, lastname, username, phoneno, password, imageName);
        UserAPI userapi = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> signupResponseCall = userapi.registerUser(user);
        signupResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(SignUp.this, "Registered", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignUp.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }


}
