package com.sheela.mobilestore.ui.more;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.adapter.CartAdapter;
import com.sheela.mobilestore.adapter.DashboardAdapter;
import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    RecyclerView recyclerView;
    List<User> userList;
   DashboardAdapter dashboardAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView=view.findViewById(R.id.recyclerviewsix);
        profile();
        return view;
    }
   private void profile(){

   }
}
