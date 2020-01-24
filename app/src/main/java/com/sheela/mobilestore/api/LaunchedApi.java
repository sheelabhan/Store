package com.sheela.mobilestore.api;

import com.sheela.mobilestore.model.Launched;
import com.sheela.mobilestore.model.Samsung;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface LaunchedApi {
    @GET("justlaunched")
    Call<List<Launched>> getlaunched();
    @Multipart
    @POST("upload")
    Call<Launched> uploadImage(@Part MultipartBody.Part img);
    @GET("justlaunched")
    Call<Launched> getImage(@Header("Authorization") String id);
}
