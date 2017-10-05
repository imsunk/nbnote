package com.nbnote.controller;

import com.nbnote.AuthenticationFilter;
import com.nbnote.model.LoginParam;
import com.nbnote.model.Note;
import com.nbnote.model.User;
import com.nbnote.model.UserSecurity;
import com.nbnote.security.TokenSecurity;
import com.nbnote.service.LoginService;
import com.nbnote.service.TokenDAO;
import com.nbnote.service.UserDAO;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        user.setPasswd("0173");
        user.setService("naver");
        user.setRole("user");
        user.setRegisterDate(date);

        Entity<User> logInEntity = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/login/register").request().put(logInEntity);
        Assert.assertNotNull(response);
        System.out.println(response);
        System.out.println(response.getStatus());
        Assert.assertEquals(200,response.getStatus());
    }

    @Test
    public void genTokenTest(){
        LoginService lgoinSvc = new LoginService();
        Date date = new Date();
        User user = new User();
        user.setId("laesunk");
        user.setAge("20");
        user.setEmail("laesunk@gmail.com");
        user.setName("김래선");
        user.setProfile("/laesunk.png");
        user.setPasswd("asdklfjasdflkajsd");
        user.setService("naver");
        user.setRole("user");
        user.setRegisterDate(date);
        Response res = lgoinSvc.makeNewAuthToken(user);
        //Entity<User> logInEntity = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
        //TokenDAO tokenDao = new TokenDAO();

        //UserDAO userDao = new UserDAO();
        System.out.println(res);
        Assert.assertNotNull(res);
        Assert.assertEquals(200, res.getStatus());
    }

    @Test
    public void signInTest(){
        LoginParam  loginParam = new LoginParam("laesunk", "0173");

        Entity<LoginParam> logInEntity = Entity.entity(loginParam, MediaType.APPLICATION_JSON_TYPE);
        Response response = target("/login").request().post(logInEntity);
        //Response response = target("/login").request().get(logInEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals(200,response.getStatus());
    }


}
