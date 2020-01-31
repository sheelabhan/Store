package com.sheela.mobilestore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.adapter.CartAdapter;
import com.sheela.mobilestore.adapter.DashboardAdapter;
import com.sheela.mobilestore.api.CartApi;
import com.sheela.mobilestore.api.UserAPI;
import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.User;
import com.sheela.mobilestore.url.Url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private ImageView imguser;
    private TextView firstname, lastname, phoneno, username;
    Button btnupdate;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        imguser = findViewById(R.id.imguser);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        phoneno = findViewById(R.id.phoneno);
        username = findViewById(R.id.username);
        btnupdate = findViewById(R.id.btnupdate);
        user = new User();

        loaduser();
    }

    private void loaduser() {
        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
//
        Call<User> ListCall8 = userAPI.getUserDetails(Url.token);
        ListCall8.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                user = response.body();
                if (response.body() != null) {

                    String imgPath = null;
                    imgPath = Url.imagePath + response.body().getImage();
                    Picasso.get().load(imgPath).into(imguser);

                }

                firstname.setText(response.body().getFirstName());
                lastname.setText(response.body().getLastName());
                phoneno.setText(response.body().getPhone());
                username.setText(response.body().getUsername());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error Message", "Error " + t.getLocalizedMessage());
                Toast.makeText(DashboardActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
