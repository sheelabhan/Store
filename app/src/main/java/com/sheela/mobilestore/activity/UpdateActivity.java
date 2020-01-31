package com.sheela.mobilestore.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.api.UserAPI;
import com.sheela.mobilestore.model.User;
import com.sheela.mobilestore.model.Usercrud;
import com.sheela.mobilestore.serverresponse.ImageResponse;
import com.sheela.mobilestore.ui.DashboardActivity;
import com.sheela.mobilestore.url.Url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    private ImageView imguser;
    private EditText etfirstname, etlastname, etphoneno, etusername;
    Button btnupdate;
    private String imagePath;
    private String imageName="";
   User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        imguser = findViewById(R.id.imguser);
        etfirstname = findViewById(R.id.firstname);
        etlastname = findViewById(R.id.lastname);
        etphoneno = findViewById(R.id.phoneno);
        etusername = findViewById(R.id.username);
        btnupdate = findViewById(R.id.btnupdate);
        user = new User();
        user =(User) getIntent().getSerializableExtra("User");

        etfirstname.setText(user.getFirstName());
        etlastname.setText(user.getLastName());
        etphoneno.setText(user.getPhone());
        etusername.setText(user.getUsername());
        String imgPath = Url.imagePath + user.getImage();

        try {
            URL url = new URL(imgPath);
            imguser.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageOnly();
                update();

            }
        });
        imguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });



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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imguser.setImageURI(uri);
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


    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    private void update() {
        String firstname = etfirstname.getText().toString();
        String lastname = etlastname.getText().toString();
        String username = etusername.getText().toString();
//        String email = etEmail.getText().toString();
        String phoneno = etphoneno.getText().toString();

        Usercrud usercrud = new Usercrud(firstname, lastname, username, phoneno, imageName);

        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
        Call<Usercrud> usercrudCall = userAPI.getupdate(Url.token,usercrud);

        usercrudCall.enqueue(new Callback<Usercrud>() {
            @Override
            public void onResponse(Call<Usercrud> call, Response<Usercrud> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(UpdateActivity.this, "code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UpdateActivity.this, DashboardActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Usercrud> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    }

