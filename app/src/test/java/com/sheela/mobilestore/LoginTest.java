package com.sheela.mobilestore;

import com.sheela.mobilestore.bll.LoginBLL;
import com.sheela.mobilestore.model.User;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginTest {

        @Test
        public void testLogin(){
       LoginBLL loginBLL= new LoginBLL();
           boolean expectedResult=false;
           boolean actualResult=loginBLL.checklogin("sheelabhan","sheela");
            assertEquals(expectedResult,actualResult) ;
        }

}

