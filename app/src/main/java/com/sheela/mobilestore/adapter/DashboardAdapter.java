package com.sheela.mobilestore.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.model.Launched;
import com.sheela.mobilestore.model.User;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>{

    Context mContext;
    List<User> userList;
    public DashboardAdapter(Context mContext, List<User>userList)
    {
        this.mContext=mContext;
        this.userList=userList;
    }

    @NonNull
    @Override
    public DashboardAdapter.DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_dashboard,parent,false);
        return new DashboardAdapter.DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.DashboardViewHolder holder, int position) {


        final User user = userList.get(position);
        String imgPath= Url.imagePath+user.getImage();
        holder.firstname.setText(user.getFirstName());
        holder.lastname.setText(user.getLastName());
        holder.phoneno.setText(user.getPhone());
        holder.username.setText(user.getUsername());
        holder.password.setText(user.getPassword());
        holder.confirmp.setText(user.getPassword());



        StrictMode.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.imguser.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder{

        ImageView imguser;
        TextView firstname, lastname,phoneno, username, password, confirmp;
        Button btnupdate;
        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            imguser=itemView.findViewById(R.id.imguser);
           firstname=itemView.findViewById(R.id.firstname);
           lastname=itemView.findViewById(R.id.lastname);
           phoneno=itemView.findViewById(R.id.phoneno);
          username=itemView.findViewById(R.id.username);

          btnupdate=itemView.findViewById(R.id.btnupdate);

        }
    }
}
