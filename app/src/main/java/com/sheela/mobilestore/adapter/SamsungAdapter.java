package com.sheela.mobilestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.cart.OppoAddToCart;
import com.sheela.mobilestore.model.Samsung;
import com.sheela.mobilestore.model.Selling;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class SamsungAdapter extends RecyclerView.Adapter<SamsungAdapter.SamsungViewHolder>{

    Context mContext;
    List<Samsung> samsungList;
    public SamsungAdapter(Context mContext, List<Samsung> samsungList)
    {
        this.mContext=mContext;
        this.samsungList=samsungList;
    }

    @NonNull
    @Override
    public SamsungAdapter.SamsungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_samsung,parent,false);
        return new SamsungAdapter.SamsungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SamsungAdapter.SamsungViewHolder holder, int position) {


        final Samsung samsung = samsungList.get(position);
        String imgPath = Url.imagePath + samsung.getImage();
        holder.tvNaame.setText(samsung.getName());
        holder.tvLoocation.setText(samsung.getLocation());
        holder.tvCoost.setText(samsung.getCost());

        StrictMode.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.imgSamsung.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.imgSamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, OppoAddToCart.class);
                intent.putExtra("image",samsung.getImage());
                intent.putExtra("name",samsung.getName());
                intent.putExtra("location",samsung.getLocation());
                intent.putExtra("cost",samsung.getCost());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return samsungList.size();
    }

    public class SamsungViewHolder extends RecyclerView.ViewHolder{

        ImageView imgSamsung;
        TextView tvNaame,tvLoocation, tvCoost;
        public SamsungViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSamsung=itemView.findViewById(R.id.imgSamsung);
            tvNaame=itemView.findViewById(R.id.tvNaame);
            tvLoocation=itemView.findViewById(R.id.tvLoocation);
            tvCoost=itemView.findViewById(R.id.tvCoost);
        }
    }
}
