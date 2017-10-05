package com.nbnote.controller;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.nbnote.model.LoginParam;
import com.nbnote.response.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nbnote.model.User;
import com.nbnote.service.LoginService;

/**
 * Created by K on 2017. 6. 29..
 */
@DeclareRoles({"admin", "user", "guest"})
@Path("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    private LoginService loginSvc = new LoginService();

    @PUT
    @PermitAll
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerUser(User user) {
        Response response = loginSvc.registerUser(user);
        return response;
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
    @PermitAll
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginParam param){
        Response response = loginSvc.authenticate(param);
        /*
        if(user==null) {
            return ResponseBuilder.createResponse( Response.Status.NOT_FOUND );
        }
        return ResponseBuilder.createResponse( Response.Status.OK, user );
        */
        return response;
    }


    @POST
    @PermitAll
    @Path("/logout/{userId}")
    @Consumes("application/json")
    public Response logOut(@PathParam("userId")String id){
        if(loginSvc.signOut(id)==true) {
            return ResponseBuilder.createResponse(Response.Status.OK);
        }else{
            return Response.status(500).build();
        }
    }
}
