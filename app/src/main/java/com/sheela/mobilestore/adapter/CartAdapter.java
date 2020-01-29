package com.sheela.mobilestore.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.Oppo;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context mContext;

    List<Cart> cartList;

    public CartAdapter(Context mContext, List<Cart> cartList) {
        this.mContext = mContext;
        this.cartList = cartList;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_detailed, viewGroup, false);
        return new CartViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, int i) {

        final Cart cart = cartList.get(i);

        cartViewHolder.name1.setText(cart.getUsername());
        cartViewHolder.product.setText(cart.getProduct_name());
        cartViewHolder.cost1.setText(cart.getCost());
        StrictMode.StrictMode();
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

      CircleImageView imgview;
        TextView name1, product, cost1;

        public CartViewHolder(@NonNull View cartView) {
            super(cartView);

          imgview=cartView.findViewById(R.id.imgview);
            name1 = cartView.findViewById(R.id.name1);
            product = cartView.findViewById(R.id.product);
            cost1 = cartView.findViewById(R.id.cost1);


        }
    }
}

