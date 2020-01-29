package com.sheela.mobilestore.api;

import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.Oppo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MyCartApi {

    @GET("mycart")
    Call<List<Cart>>getCart();

    @GET("mycart")
    Call<Cart> getImage(@Header("Authorization") String token);

    @POST("mycart")
    Call<Void> addtoitem(@Body Cart cart);
}
