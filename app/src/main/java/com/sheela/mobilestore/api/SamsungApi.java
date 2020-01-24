package com.sheela.mobilestore.api;

import com.sheela.mobilestore.model.Samsung;
import com.sheela.mobilestore.model.Selling;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface SamsungApi {
    @GET("samsung")
    Call<List<Samsung>> getsamsung();
    @Multipart
    @POST("upload")
    Call<Samsung> uploadImage(@Part MultipartBody.Part img);
    @GET("samsung")
    Call<Samsung> getImage(@Header("Authorization") String id);
}
