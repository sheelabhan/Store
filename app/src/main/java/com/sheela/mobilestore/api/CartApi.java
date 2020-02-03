package com.sheela.mobilestore.api;

import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.Cartcrud;
import com.sheela.mobilestore.model.Selling;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CartApi {
    @GET("mycart")
    Call<List<Cartcrud>> getcart();
    @Multipart
    @POST("upload")
    Call<Cartcrud> uploadImage(@Part MultipartBody.Part img);
    @GET("mycart")
    Call<Cartcrud> getImage(@Header("Authorization") String token);

    @DELETE("mycart")
    Call<Cartcrud> deleteItems(@Header("Authorization") String token, @Path("id") String id);
}
