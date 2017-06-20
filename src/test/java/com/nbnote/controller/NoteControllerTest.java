package com.nbnote.controller;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * Created by K on 2017. 6. 18..
 */
public class NoteControllerTest extends JerseyTest{
    @Override
    protected Application configure() {
        return  new ResourceConfig(NoteController.class);
    }

    @Test
    public void NoteListTest(){
        Response response = target("/note/lskim").request().get();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());

    }
}
