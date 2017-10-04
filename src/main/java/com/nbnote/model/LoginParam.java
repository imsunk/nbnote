package com.nbnote.model;

/**
 * Created by K on 2017. 10. 4..
 */
public class LoginParam {
    private String id;
    private String passwd;

    public LoginParam(){}

    public LoginParam(String id, String passwd) {
        this.id = id;
        this.passwd = passwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
