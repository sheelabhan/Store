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
import com.sheela.mobilestore.model.Contacts;
import com.sheela.mobilestore.model.Launched;
import com.sheela.mobilestore.model.Samsung;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class LaunchedAdapter  extends RecyclerView.Adapter<LaunchedAdapter.LaunchedViewHolder>{

    Context mContext;
    List<Launched> launchedList;
    public LaunchedAdapter(Context mContext, List<Launched>launchedList)
    {
        this.mContext=mContext;
        this.launchedList=launchedList;
    }

    @NonNull
    @Override
    public LaunchedAdapter.LaunchedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_launched,parent,false);
        return new LaunchedAdapter.LaunchedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchedAdapter.LaunchedViewHolder holder, int position) {


        final Launched launched= launchedList.get(position);
        String imgPath= Url.imagePath+launched.getImage();
        holder.tvNaamee.setText(launched.getName());
        holder.tvLoc.setText(launched.getLocation());
        holder.tvCosst.setText(launched.getCost());

        StrictMode.StrictMode();
        try{
            URL url=new URL(imgPath);
            holder.imgsheela.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        holder.imgsheela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, OppoAddToCart.class);
                intent.putExtra("image",launched.getImage());
                intent.putExtra("name",launched.getName());
                intent.putExtra("location",launched.getLocation());
                intent.putExtra("cost",launched.getCost());

                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return launchedList.size();
    }

    public class LaunchedViewHolder extends RecyclerView.ViewHolder{

        ImageView imgsheela;
        TextView tvNaamee,tvLoc,tvCosst;
        public LaunchedViewHolder(@NonNull View itemView) {
            super(itemView);

            imgsheela=itemView.findViewById(R.id.imgsheela);
            tvNaamee=itemView.findViewById(R.id.tvNaamee);
            tvLoc=itemView.findViewById(R.id.tvLoc);
            tvCosst=itemView.findViewById(R.id.tvCosst);
        }
    }

}
