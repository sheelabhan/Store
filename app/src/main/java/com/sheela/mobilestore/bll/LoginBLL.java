package com.sheela.mobilestore.bll;

import com.sheela.mobilestore.api.UserAPI;
import com.sheela.mobilestore.model.username;
import com.sheela.mobilestore.serverresponse.SignUpResponse;
import com.sheela.mobilestore.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {
        com.sheela.mobilestore.model.username Username= new username(username, password);
        UserAPI usersAPI = Url.getInstance().create(UserAPI.class);


        Call<SignUpResponse> usersCall = usersAPI.checklogin(Username);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}

