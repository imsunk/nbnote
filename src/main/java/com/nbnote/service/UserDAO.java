package com.nbnote.service;

import com.nbnote.exception.UserExistingException;
import com.nbnote.exception.UserNotFoundException;
import com.nbnote.model.LoginParam;
import com.nbnote.model.User;
import com.nbnote.model.UserSecurity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017. 9. 3..
 */
public class UserDAO extends Service{
    final static Logger logger = Logger.getLogger( UserDAO.class );

    private Connection connection = null;

    public boolean createUser(User user, String hashedPasswd ) throws UserExistingException {
        StringBuilder query = new StringBuilder("insert into user(id, service, passwd, name,age,profile,email,registerDate)" +
                " values (?,?,?,?,?,?,?,?)");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, user.getId());
            ptmt.setString(2, user.getService());
            ptmt.setString(3, hashedPasswd);
            ptmt.setString(4, user.getName());
            ptmt.setString(5, user.getAge());
            ptmt.setString(6, user.getProfile());
            ptmt.setString(7, user.getEmail());
            ptmt.setTimestamp(8, new java.sql.Timestamp(new Date().getTime()));

            ptmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ptmt.close();
                con.close();
            } catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return true;
    }

    public boolean checkUserById(String id) throws UserNotFoundException{
        StringBuilder query = new StringBuilder("select * from user where id=?");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, id);
            rs = ptmt.executeQuery();
            rs.next();

            if(rs.getRow()>=1) {
                return false;
            }else{
                throw new UserNotFoundException( id );
            }

        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            try {
                rs.close();
                ptmt.close();
                con.close();
            } catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return true;
    }

    public User getUser(String id)  throws UserNotFoundException{
        StringBuilder query = new StringBuilder("select * from user where id=?");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, id);
            rs = ptmt.executeQuery();

            rs.next();
            if (rs.getRow() >= 1) {
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
                con.close();
            } catch (SQLException e) {
                logger.debug(e.getMessage());
            }
        }

        return user;
    }

    public User getUser(LoginParam param){
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
                con.close();
            } catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return user;
    }

}