package com.nbnote.controller;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;

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
}
