package com.nbnote.controller;

import com.nbnote.security.Token;
import com.nbnote.security.TokenService;
import com.nbnote.model.Auth;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by K on 2017. 6. 18..
 */

@Path("/security")
public class AuthController  {
    private static final TokenService tokenSvc = new TokenService();
    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Auth newToken(@PathParam("userId") String id, @Context HttpServletRequest requestContext, @HeaderParam("user-agent") String userAgent) throws Exception {
        Token token = tokenSvc.createToken(id,requestContext.getRemoteAddr(),userAgent);
        Auth auth = null;
        try {
             auth = new Auth(token.getUserId(), token.getToken());
        }catch (Exception e){
            e.printStackTrace();
        }
        return auth;
    }

    @DELETE
    public Response logout(String token) throws Exception {
        tokenSvc.deleteToken(token);

        return Response.ok().build();
    }

    public static boolean checkToken(String id, String token) throws Exception {
        String tokenById = tokenSvc.getToken(id).getToken();
        if (tokenById.equals(token)){
            return true;
        }
        return false;

    }
}
