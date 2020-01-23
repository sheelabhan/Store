package com.sheela.mobilestore.api;

import com.sheela.mobilestore.model.Selling;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BestSellingApi {
    @GET("selling")
    Call<List<Selling>> getbestsellings();
    @Multipart
    @POST("upload")
    Call<Selling> uploadImage(@Part MultipartBody.Part img);
    @GET("selling")
    Call<Selling> getImage(@Header("Authorization") String id);
}
