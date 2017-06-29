package com.nbnote.controller;

import com.nbnote.model.User;
import com.nbnote.service.LoginService;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

}
