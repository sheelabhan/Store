package com.sheela.mobilestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.cart.OppoAddToCart;
import com.sheela.mobilestore.cart.SamsungAddToCart;
import com.sheela.mobilestore.model.Samsung;
import com.sheela.mobilestore.model.Selling;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class SamsungAdapter extends RecyclerView.Adapter<SamsungAdapter.SamsungViewHolder> implements Filterable {

    Context mContext;
    List<Samsung> samsungList;
    List<Samsung> samsungListFilter;
    public SamsungAdapter(Context mContext, List<Samsung> samsungList)
    {
        this.mContext=mContext;
        this.samsungList=samsungList;
        this.samsungListFilter=samsungList;
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
        System.out.println(position+"");
        String imgPath = Url.imagePath + samsungListFilter.get(position).getImage();
        holder.tvNaame.setText(samsungListFilter.get(position).getName());
        holder.tvLoocation.setText(samsungListFilter.get(position).getLocation());
        holder.tvCoost.setText(samsungListFilter.get(position).getCost());
        StrictMode.StrictMode();
        try{
            URL url=new URL(imgPath);
            holder.imgSamsung.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        final Samsung samsung = samsungListFilter.get(position);
        holder.imgSamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, SamsungAddToCart.class);
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
        return samsungListFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if(Key.isEmpty()){
                    samsungListFilter= samsungList;
                }else {
                    List<Samsung> samsungArrayList= new ArrayList<>();
                    for(Samsung row: samsungList){
                        if(row.getName().toLowerCase().contains(Key.toLowerCase())){
                            samsungArrayList.add(row);
                        }

                    }
                    samsungListFilter=samsungArrayList;
                }

                FilterResults filterResults= new FilterResults();
                filterResults.values= samsungListFilter;
                return filterResults;


            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
            samsungListFilter= (List<Samsung>) results.values;
            notifyDataSetChanged();
            }
        };
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
