package com.sheela.mobilestore.ui.more;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    RecyclerView recyclerViewsix;

    List<User> userList;
    User user;
   DashboardAdapter dashboardAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerViewsix=view.findViewById(R.id.recyclerviewsix);
        user = new User();
        return view;
    }

   }


