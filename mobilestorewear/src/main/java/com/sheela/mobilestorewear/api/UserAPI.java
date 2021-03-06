package com.sheela.mobilestorewear.api;


import com.sheela.mobilestorewear.model.username;
import com.sheela.mobilestorewear.serverresponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserAPI {

  @POST("user/login")
  Call<SignUpResponse> checklogin(@Body username Username) ;

}
