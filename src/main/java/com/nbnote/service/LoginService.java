package com.nbnote.service;

import com.nbnote.model.LoginParam;
import com.nbnote.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        } finally {
            try {
                ptmt.close();
                conn.getConnection().close();
            } catch (SQLException e){
                logger.debug(e.getMessage());
            }
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
        } finally {
            try {
                ptmt.close();
                conn.getConnection().close();
            } catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }
    }
    
    public User signIn(LoginParam param){
        StringBuilder query = new StringBuilder("select * from user where id=? and passwd=?");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, param.getId());
            ptmt.setString(2, param.getPasswd());
            rs = ptmt.executeQuery();

            rs.next();
            if(rs.getRow()>=1) {
                user = new User();
                user.setId(rs.getString(1));
                user.setName(rs.getString(4));
                user.setAge(rs.getString(5));
                user.setProfile(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setRegisterDate(rs.getDate(8));
            }

        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            try {
                rs.close();
                ptmt.close();
                conn.getConnection().close();
            } catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return user;
    }

}
