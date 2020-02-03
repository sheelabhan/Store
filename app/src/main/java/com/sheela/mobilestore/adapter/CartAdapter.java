package com.sheela.mobilestore.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.StrictModeClass.StrictMode;
import com.sheela.mobilestore.api.CartApi;
import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.Oppo;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context mContext;

    List<Cart> cartList;

    CartAdapter a = this;

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
        String imgPath = Url.imagePath + cart.getImage();


        try {
            URL url = new URL(imgPath);
            cartViewHolder.imgview.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        cartViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CartApi api= Url.getInstance().create(CartApi.class);
                                Call<Void> voidCall=api.deleteItems(cartViewHolder.name1.getText().toString());

                                voidCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Toast.makeText(mContext, "Items procced to delievery", Toast.LENGTH_SHORT).show();
                                        cartList.remove(cart);
                                        a.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(mContext, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("cancel",null);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();



            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

      CircleImageView imgview;
        TextView name1, product, cost1;
        Button button;
        public CartViewHolder(@NonNull View cartView) {
            super(cartView);

          imgview=cartView.findViewById(R.id.imgview);
            name1 = cartView.findViewById(R.id.name1);
            product = cartView.findViewById(R.id.product);
            cost1 = cartView.findViewById(R.id.cost1);
            button=cartView.findViewById(R.id.btnprocced);



        }
    }
}

