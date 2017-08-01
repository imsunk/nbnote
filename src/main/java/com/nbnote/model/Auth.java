package com.nbnote.model;

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
