package com.nbnote.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.nbnote.model.LoginParam;
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
    @Path("/register")
    @Consumes("application/json")
    public Response registerUser(User user) {
        loginSvc.registerUser(user);
        return Response.ok().build();
    }

    /*
    @POST
    @Path("{userId}")
    @Consumes("application/json")
    public Response modUserInfo(User user){
        loginSvc.modUserInfo(user);
        return Response.ok().build();
    }
    */
    
    @POST
    @Consumes("application/json")
    public Response login(LoginParam param){
        User user = loginSvc.signIn(param);
        if(user==null){
            return Response.status(404).build();
        }
        return Response.ok().build();
    }    
}
