package com.nbnote.controller;

import com.nbnote.model.UserSecurity;
import com.nbnote.response.ResponseBuilder;
import com.nbnote.security.Token;
import com.nbnote.security.TokenService;
import com.nbnote.model.Auth;
import com.nbnote.service.TokenDAO;
import com.nbnote.service.UserDAO;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by K on 2017. 6. 18..
 */

@Path("/auth")
public class AuthController  {
    private static final TokenDAO tokenSvc = new TokenDAO();

    @PermitAll
    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(@PathParam("userId") String id){
        UserSecurity userSec = tokenSvc.getUserAuthentication(id);
        return ResponseBuilder.createResponse( Response.Status.OK, userSec );
    }


}
