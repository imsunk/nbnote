package com.nbnote.security;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * Created by K on 2017. 6. 18..
 */
public class Token {

    public static final int DEFAULT_EXPIRE_DATE = 1;	//토큰 만기기간 지정(단위:개월)
    private String token;
    private String userId;
    private Date expireDate;
    private Date genDate;
    private String ip;
    private String platform;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    public Date getGenDate() {
        return this.genDate;
    }
    public void setGenDate(Date issueDate) {
        this.genDate = issueDate;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public static String generateToken() {
        Random random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
    /*
    @Override
    public String toString() {
        return "Token [token=" + token + ", userId=" + userId + ", expireDate=" + expireDate + ", issueDate="
                + genDate + "]";
    }
    */

}
