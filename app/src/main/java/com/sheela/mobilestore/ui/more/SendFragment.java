package com.sheela.mobilestore.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.ui.DashboardActivity;

public class SendFragment extends Fragment {

   private Button btnProfile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_send, container, false);
       btnProfile=view.findViewById(R.id.btnProfile);
       btnProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent= new Intent(getContext(), DashboardActivity.class);
               startActivity(intent);
           }
       });
        return view;
    }
}