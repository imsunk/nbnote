package com.nbnote.controller;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nbnote.model.User;
import com.nbnote.service.LoginService;

/**
 * Created by K on 2017. 6. 29..
 */
@Path("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    private LoginService loginSvc = new LoginService();

    @PUT
    public Response registerUser(User user) {
        loginSvc.registerUser(user);
        return Response.ok().build();
    }

    @PUT
    @Path("{userId}")
    public Response modUserInfo(User user){
        loginSvc.modUserInfo(user);
        return Response.ok().build();
    }
    
    @POST
    @Path("")
    public Response login(User user){
        loginSvc.modUserInfo(user);
        return Response.ok().build();
    }    
}
