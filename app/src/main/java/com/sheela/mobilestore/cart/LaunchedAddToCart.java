package com.sheela.mobilestore.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.api.MyCartApi;
import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.ui.mycart.ToolsFragment;
import com.sheela.mobilestore.url.Url;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchedAddToCart extends AppCompatActivity {
    private TextView namee,loocationn,cosst;
    private ImageView imggg;
    private Button btnaddtocartlaunched;
    Cart cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launched_add_to_cart);
        namee=findViewById(R.id.namelaunchedaddtocart);
        loocationn=findViewById(R.id.locationlaunchedaddtocart);
       cosst=findViewById(R.id.costlaunchedaddtocart);
        imggg=findViewById(R.id.addlaunched);
        btnaddtocartlaunched=findViewById(R.id.btnaddtocartsamsung);


        Bundle bundle=getIntent().getExtras();

        if (bundle != null) {

            String imagename = bundle.getString("image");

            final String imgPath ="http://10.0.2.2:3001/uploads/" + imagename;

            try {
                URL url=new URL(imgPath);
                imggg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            }catch (Exception e){
                e.printStackTrace();
            }
            namee.setText(bundle.getString("name"));
            loocationn.setText(bundle.getString("location"));
            cosst.setText(bundle.getString("cost"));
        }

      btnaddtocartlaunched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(LaunchedAddToCart.this);
                builder.setMessage("Are you sure")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                oppoAddtoCart();
                            }
                        })
                        .setNegativeButton("cancel",null);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }

    private void oppoAddtoCart() {

        MyCartApi myCartApi= Url.getInstance().create(MyCartApi.class);
        Call<Cart> cartCall = myCartApi.getImage(Url.token);
//        String user_name = "sheela";
//        String product_name = namee.getText().toString();
//        String prduct_cost=cosst.getText().toString();


//        Cart cart=new Cart(user_name,product_name,prduct_cost);

        Call<Void> voidCall=myCartApi.addtoitem(cart);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(LaunchedAddToCart.this, "Item added to cart successfully", Toast.LENGTH_SHORT).show();
            }




            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(LaunchedAddToCart.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    }

