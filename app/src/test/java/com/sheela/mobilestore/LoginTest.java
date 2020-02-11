package com.sheela.mobilestore;

import com.sheela.mobilestore.api.UserAPI;
import com.sheela.mobilestore.bll.LoginBLL;
import com.sheela.mobilestore.model.User;
import com.sheela.mobilestore.serverresponse.SignUpResponse;
import com.sheela.mobilestore.url.Url;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LoginTest {

        @Test
       public void testLoginFail(){
            LoginBLL loginBLL= new LoginBLL();
            boolean result= loginBLL.checklogin("sheelabhandari","sheela");
            assertTrue(result);
        }
    @Test
    public void testLoginTest(){
        LoginBLL loginBLL= new LoginBLL();
        boolean result= loginBLL.checklogin("sheelabhan","sheela");
        assertFalse(result);
    }
    @Test
    public void registerpass(){
            User user= new User();
        UserAPI userAPI= Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse>signUpResponseCall=userAPI.registerUser(user);
        try{
            Response<SignUpResponse> register=signUpResponseCall.execute();
            if(register.isSuccessful() && register.body().getStatus().equals("SignUp Success!")){
                assertEquals(true,signUpResponseCall);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Test
    public void registerfail(){
        User user= new User();
        UserAPI userAPI= Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse>signUpResponseCall=userAPI.registerUser(user);
        try{
            Response<SignUpResponse> register=signUpResponseCall.execute();
            if(register.isSuccessful() && register.body().getStatus().equals("SignUp Success!")){
                assertEquals(true,signUpResponseCall);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


