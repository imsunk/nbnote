package com.nbnote.controller;

import com.nbnote.model.UserSecurity;
import com.nbnote.security.TokenSecurity;
import com.nbnote.service.TokenDAO;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * Created by K on 2017. 6. 18..
 */
public class AuthControllerTest extends JerseyTest{
    @Override
    protected Application configure() {
        return  new ResourceConfig(AuthController.class);
}

    @Test
    public void AuthIdTest(){
        String response = target("/auth/laesunk").request().get(String.class);
        Assert.assertNotNull(response);
        System.out.println(response);
    }

    @Test
    public void validToken(){
        String userId = "laesunk";
        UserSecurity userSec = getToken(userId);
        try {
            String id = TokenSecurity.validateJwtToken(userSec.getToken());
            Assert.assertEquals(id,"lasesunk");
            System.out.println(id);
        } catch ( InvalidJwtException e ) {
            System.out.println(e.getMessage());
        }

    }

    public UserSecurity getToken(String id){
        String token =  "";
        TokenDAO tokenDao = new TokenDAO();
        UserSecurity userSec = tokenDao.getUserAuthentication(id);

        return userSec;
    }
}
