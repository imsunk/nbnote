package com.nbnote.model;

import java.util.Date;

/**
 * Created by K on 2017. 9. 3..
 */
public class UserSecurity {

    private String token = null;
    private String role = null;
    private String id = null;
    private Date expire_time = null;

    public UserSecurity() {}

    public UserSecurity( String id,  String token ) {
        this.id = id;
        this.token = token;
    }

    public UserSecurity( String id, String token, String role ) {
        this.id = id;
        this.token = token;
        this.role = role;
    }

    public UserSecurity( String id, String token, String role, Date expire_time ) {
        this.id = id;
        this.token = token;
        this.role = role;
        this.expire_time = expire_time;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(Date expire_time) {
        this.expire_time = expire_time;
    }

    @Override
    public String toString() {
        return "UserSecurity [id="+ this.getId() + ", role=" + role + ", token=" + token + "]";
    }
}