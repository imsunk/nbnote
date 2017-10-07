package com.nbnote.service;

import com.nbnote.exception.UserNotFoundException;
import com.nbnote.model.UserSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017. 10. 4..
 */
public class TokenDAO extends Service{
    final static Logger logger = LoggerFactory.getLogger( TokenDAO.class );

    public UserSecurity getUserAuthentication(String id ) throws UserNotFoundException {
        logger.debug( "getUserAuthentication: " + id );

        UserSecurity userSecurity = null;
        Connection con = conn.getConnection();
        ResultSet rs = null;
        PreparedStatement ptmt = null;

        try {
            String query = "SELECT id, token, role, expire_date FROM token WHERE id=?;";
            ptmt = con.prepareStatement(query);
            ptmt.setString(1, id);
            rs = ptmt.executeQuery();

            if( rs.next() ) {
                String token = rs.getString("token");
                String role = rs.getString("role");
                Date expire_time = rs.getDate("expire_date");

                userSecurity = new UserSecurity(id, token, role, expire_time );
            }
            else {
                throw new UserNotFoundException( id );
            }

        } catch ( SQLException e ) {
            logger.debug( e.getClass().getName() + ": " + e.getMessage() );
        }
        finally {
            try {
                rs.close();
                ptmt.close();
                con.close();
            } catch ( SQLException e ) {
                logger.warn( e.getMessage() );
            }
        }

        return userSecurity;
    }

    public boolean saveNewToken( UserSecurity userToken ) {
        logger.debug("setUserAuthentication: " + userToken.getId());
        StringBuilder query = new StringBuilder("insert into token(id, token, expire_date, role) values(?,?,?,?)");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, userToken.getId());
            ptmt.setString(2, userToken.getToken());
            ptmt.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            ptmt.setString(4, userToken.getRole());

            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            try {
                ptmt.close();
                con.close();
            } catch (SQLException e) {
                logger.debug(e.getMessage());
            }
        }

        return true;
    }

    public boolean setUserAuthentication( UserSecurity user ) throws UserNotFoundException {
        logger.debug( "setUserAuthentication: " + user.getId() );
        /*
        추가 시간 로직 rrr
         */
        StringBuilder query = new StringBuilder("update user set token=?, role=?, expire_date=? ) where id=?");
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1, user.getToken());
            ptmt.setString(2, user.getRole());
            ptmt.setString(3, "20170301");
            ptmt.setString(4, user.getId());

            ptmt.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            try {
                ptmt.close();
                con.close();
            } catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return true;
        /*
        PreparedStatement stmt = null;

        try {
            // prepare query
            StringBuffer query = new StringBuffer();
            query.append( "UPDATE USER SET " );

            boolean comma = false;
            List<String> prepare = new ArrayList<String>();
            if( user.getPassword() != null ) {
                query.append( "password=?" );
                comma = true;
                prepare.add( user.getPassword() );
            }

            if( user.getToken() != null ) {
                if( comma ) query.append(",");
                query.append( "token=?" );
                comma = true;
                prepare.add( user.getToken() );
            }

            if( user.getRole() != null ) {
                if( comma ) query.append(",");
                query.append( "role=?" );
                prepare.add( user.getRole() );
            }

            query.append(" WHERE id=?");
            stmt = connection.prepareStatement( query.toString() );

            for( int i = 0; i < prepare.size(); i++ ) {
                stmt.setString( i+1, prepare.get(i) );
            }

            stmt.setInt( prepare.size() + 1, Integer.parseInt( user.getId() ) );

            stmt.executeUpdate();

        } catch ( SQLException e ) {
            logger.debug( e.getClass().getName() + ": " + e.getMessage() );
        }
        finally {
            try {
                stmt.close();
            } catch ( SQLException e ) {
                logger.warn( e.getMessage() );
            }
        }*/

    }
}
