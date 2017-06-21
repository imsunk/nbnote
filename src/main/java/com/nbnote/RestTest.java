package com.nbnote; /**
 * Created by K on 2017. 6. 13..
 */
import com.nbnote.model.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class RestTest {

    @GET
    @Path("/get/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Test getMsg(@PathParam("param") String id) {

        Test auth = new Test(id,"aac");
        return auth;
    }

}