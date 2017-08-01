package com.nbnote.service;

import com.nbnote.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by K on 2017. 6. 29..
 */
public class LoginService extends Service {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public void registerUser(User user) {
        StringBuilder query = new StringBuilder("insert into user(id, service, passwd, name,age,profile,email,registerDate)" +
                " values (?,?,?,?,?,?,?,?)");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, user.getId());
            ptmt.setString(2, user.getService());
            ptmt.setString(3, user.getPasswd());
            ptmt.setString(4, user.getName());
            ptmt.setString(5, user.getAge());
            ptmt.setString(6, user.getProfile());
            ptmt.setString(7, user.getEmail());
            ptmt.setTimestamp(8, new java.sql.Timestamp(user.getRegisterDate().getTime()));

            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public void modUserInfo(User user) {
        StringBuilder query = new StringBuilder("update user set service=?, name=?, age=?, profile=?,email=?) where id=?");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, user.getService());
            ptmt.setString(2, user.getName());
            ptmt.setString(3, user.getAge());
            ptmt.setString(4, user.getProfile());
            ptmt.setString(5, user.getEmail());
            ptmt.setString(6, user.getId());

            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }
    
    public void login(User user){
        StringBuilder query = new StringBuilder("update user set service=?, name=?, age=?, profile=?,email=?) where id=?");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, user.getService());
            ptmt.setString(2, user.getName());
            ptmt.setString(3, user.getAge());
            ptmt.setString(4, user.getProfile());
            ptmt.setString(5, user.getEmail());
            ptmt.setString(6, user.getId());

            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }
}
