package com.sheela.mobilestore.api;

import com.sheela.mobilestore.model.Launched;
import com.sheela.mobilestore.model.Oppo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface OppoApi {
    @GET("opoo")
    Call<List<Oppo>>getoppo();
    @Multipart
    @POST("upload")
    Call<Oppo> uploadImage(@Part MultipartBody.Part img);
    @GET("opoo")
    Call<Oppo> getImage(@Header("Authorization") String id);
}
