package com.sheela.mobilestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.OppoAddToCart;
import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.model.Oppo;
import com.sheela.mobilestore.model.Samsung;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class OppoAdapter extends RecyclerView.Adapter<OppoAdapter.OppoViewHolder>{

    Context mContext;
    List<Oppo> oppoList;
    public OppoAdapter(Context mContext, List<Oppo> oppoList)
    {
        this.mContext=mContext;
        this.oppoList=oppoList;
    }

    @NonNull
    @Override
    public OppoAdapter.OppoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_oppo,parent,false);
        return new OppoAdapter.OppoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OppoAdapter.OppoViewHolder holder, int position) {


        final Oppo oppo= oppoList.get(position);
        String imgPath= Url.imagePath+oppo.getImage();
        holder.tvopponame.setText(oppo.getName());
        holder.tvoppoloc.setText(oppo.getLocation());
        holder.tvoppocost.setText(oppo.getCost());

        StrictMode.StrictMode();
        try{
            URL url=new URL(imgPath);
            holder.imgOpoo.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        holder.imgOpoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, OppoAddToCart.class);
                intent.putExtra("image",oppo.getImage());
                intent.putExtra("name",oppo.getName());
                intent.putExtra("location",oppo.getLocation());
                intent.putExtra("cost",oppo.getCost());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return oppoList.size();
    }

    public class OppoViewHolder extends RecyclerView.ViewHolder{

        ImageView imgOpoo;
        TextView tvopponame,tvoppoloc, tvoppocost;
        public OppoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgOpoo=itemView.findViewById(R.id.imgOppo);
            tvopponame=itemView.findViewById(R.id.tvopponame);
            tvoppoloc=itemView.findViewById(R.id.tvoppoloc);
            tvoppocost=itemView.findViewById(R.id.tvoppocost);
        }
    }
}
