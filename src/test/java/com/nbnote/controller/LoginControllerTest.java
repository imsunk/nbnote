package com.nbnote.controller;

import com.nbnote.model.LoginParam;
import com.nbnote.model.Note;
import com.nbnote.model.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by K on 2017. 6. 29..
 */
public class LoginControllerTest extends JerseyTest {
    @Override
    protected Application configure() {
        return  new ResourceConfig(LoginController.class);
    }

    @Test
    public void registerUserTest(){
        Date date = new Date();
        User user = new User();
        user.setId("laesunk12");
        user.setAge("20");
        user.setEmail("laesunk@gmail.com");
        user.setName("김래선");
        user.setProfile("/laesunk.png");
        user.setPasswd("asdklfjasdflkajsd");
        user.setService("naver");
        user.setRegisterDate(date);

        Entity<User> logInEntity = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/login/register").request().put(logInEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatus());
    }

    @Test
    public void signInTest(){
        LoginParam  loginParam = new LoginParam("laesunk", "asdklfjasdflkajsd");

        Entity<LoginParam> logInEntity = Entity.entity(loginParam, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/login").request().post(logInEntity);
        //Response response = target("/login").request().get(logInEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatus());
    }
}
