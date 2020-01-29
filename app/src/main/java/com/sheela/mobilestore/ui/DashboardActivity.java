package com.sheela.mobilestore.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.adapter.CartAdapter;
import com.sheela.mobilestore.adapter.DashboardAdapter;
import com.sheela.mobilestore.api.CartApi;
import com.sheela.mobilestore.api.UserAPI;
import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.User;
import com.sheela.mobilestore.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard2);

    }

}
