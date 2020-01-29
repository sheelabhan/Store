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
import com.sheela.mobilestore.cart.SellingAddToCart;
import com.sheela.mobilestore.model.Selling;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class SellingAdapter extends RecyclerView.Adapter<SellingAdapter.SellingViewHolder>{

    Context mContext;
    List<Selling> sellingList;
    public SellingAdapter(Context mContext, List<Selling> sellingList)
    {
        this.mContext=mContext;
        this.sellingList=sellingList;
    }

    @NonNull
    @Override
    public SellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view,parent,false);
        return new SellingAdapter.SellingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellingViewHolder holder, int position) {


        final Selling selling= sellingList.get(position);
        String imgPath= Url.imagePath+selling.getImage();
        holder.tvName.setText(selling.getName());
        holder.tvLocation.setText(selling.getLocation());
        holder.tvCost.setText(selling.getCost());

        StrictMode.StrictMode();
        try{
            URL url=new URL(imgPath);
            holder.imgProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        holder.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, SellingAddToCart.class);
                intent.putExtra("image",selling.getImage());
                intent.putExtra("name",selling.getName());
                intent.putExtra("location",selling.getLocation());
                intent.putExtra("cost",selling.getCost());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sellingList.size();
    }

    public class SellingViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProfile;
        TextView tvName,tvLocation, tvCost;
        public SellingViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile=itemView.findViewById(R.id.imgProfile);
            tvName=itemView.findViewById(R.id.tvName);
            tvLocation=itemView.findViewById(R.id.tvLocation);
            tvCost=itemView.findViewById(R.id.tvCost);
        }
    }
}
