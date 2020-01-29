package com.sheela.mobilestore.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.sheela.mobilestore.url.Url;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellingAddToCart extends AppCompatActivity {
    private TextView nama,loc,cos;
    private ImageView image;
    private Button btnaddtocartselling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_add_to_cart);
      nama=findViewById(R.id.namesellingaddtocart);
        loc=findViewById(R.id.locationsellingaddtocart);
        cos=findViewById(R.id.costsellingaddtocart);
       image=findViewById(R.id.addselling);
      btnaddtocartselling=findViewById(R.id.btnaddtocartselling);

        Bundle bundle=getIntent().getExtras();

        if (bundle != null) {

            String imagename = bundle.getString("image");

            final String imgPath ="http://10.0.2.2:3001/uploads/" + imagename;

            try {
                URL url=new URL(imgPath);
               image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            }catch (Exception e){
                e.printStackTrace();
            }
            nama.setText(bundle.getString("name"));
            loc.setText(bundle.getString("location"));
            cos.setText(bundle.getString("cost"));
        }

    btnaddtocartselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(SellingAddToCart.this);
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
        String user_name = "sheela";
        String product_name = nama.getText().toString();
        String prduct_cost=cos.getText().toString();

        Cart cart=new Cart(user_name,product_name,prduct_cost);

        Call<Void> voidCall=myCartApi.addtoitem(cart);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SellingAddToCart.this, "Item added to cart successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SellingAddToCart.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    }

