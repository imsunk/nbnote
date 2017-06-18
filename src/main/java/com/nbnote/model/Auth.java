package com.nbnote.model;

import com.nbnote.auth.Token;
import com.nbnote.auth.TokenService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by K on 2017. 6. 18..
 */
public class Auth {
    private String id;
    private String token;

    public Auth(String id, String token) {
        this.id = id;
        this.token = token;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
