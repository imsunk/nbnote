package com.nbnote.controller;

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
        user.setId("laesunk");
        user.setAge("20");
        user.setEmail("laesunk@gmail.com");
        user.setName("김래선");
        user.setProfile("/laesunk.png");
        user.setPasswd("asdklfjasdflkajsd");
        user.setService("naver");
        user.setRegisterDate(date);

        Entity<User> logInEntity = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/login").request().put(logInEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatus());
    }
}
