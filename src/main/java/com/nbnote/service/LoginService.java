package com.nbnote.service;

import com.nbnote.AuthenticationFilter;
import com.nbnote.exception.UserExistingException;
import com.nbnote.exception.UserNotFoundException;
import com.nbnote.model.LoginParam;
import com.nbnote.model.User;
import com.nbnote.model.UserSecurity;
import com.nbnote.response.ResponseBuilder;
import com.nbnote.security.PasswordSecurity;
import com.nbnote.security.TokenSecurity;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by K on 2017. 6. 29..
 */
public class LoginService extends Service {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public Response registerUser(User user) {
        UserDAO userDao = new UserDAO();
        UserSecurity userSecurity = new UserSecurity(user.getPasswd(),user.getId(),user.getRole());

        try {
            try {
                // check if user no registered already
                userDao.checkUserById( user.getId() );
                throw new UserExistingException( user.getId() );
            }
            catch( UserNotFoundException e ) {
                String passwd =  PasswordSecurity.generateHash( user.getPasswd() );
                userDao.createUser(user, passwd);
                // authenticate user
                return makeNewAuthToken(user);
            }
        }
        catch ( UserExistingException e ) {
            return ResponseBuilder.createResponse( Response.Status.CONFLICT, e.getMessage() );
        }
        catch ( Exception e ) {
            return ResponseBuilder.createResponse( Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

   public Response makeNewAuthToken(User user ) {
        UserDAO userDao = new UserDAO();
        TokenDAO tokenDao = new TokenDAO();

        String token="";

        try {
            token = TokenSecurity.generateJwtToken(user.getId());
            UserSecurity sec = new UserSecurity(user.getId(), token, user.getRole(), new Date());
            tokenDao.saveNewToken(sec);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(AuthenticationFilter.AUTHORIZATION_PROPERTY, token);

            // Return the token on the response
            return ResponseBuilder.createResponse(Response.Status.OK, map);
        } catch (UserNotFoundException e) {
            return ResponseBuilder.createResponse(Response.Status.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return ResponseBuilder.createResponse(Response.Status.UNAUTHORIZED, e.getMessage());
        }
    }



    public Response authenticate(LoginParam param) {
        UserDAO userDao = new UserDAO();
        TokenDAO tokenDao = new TokenDAO();

        try {
            User user = userDao.getUser(param.getId());

            if( PasswordSecurity.validatePassword( param.getPasswd(), user.getPasswd() ) == false ) {
                return ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED );
            }
            /*
            // generate a token for the user
            String token = TokenSecurity.generateJwtToken( id );
            // write the token to the database
            UserSecurity sec = new UserSecurity(id, token);
            tokenDao.setUserAuthentication(sec);
            */
            String token = tokenDao.getUserAuthentication(param.getId()).getToken();

            Map<String,Object> map = new HashMap<String,Object>();
            map.put( AuthenticationFilter.AUTHORIZATION_PROPERTY, token );

            // Return the token on the response
            return ResponseBuilder.createResponse( Response.Status.OK, map );
        }
        catch( UserNotFoundException e ) {
            return ResponseBuilder.createResponse( Response.Status.NOT_FOUND, e.getMessage() );
        }
        catch( Exception e ) {
            return ResponseBuilder.createResponse( Response.Status.UNAUTHORIZED );
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

    /*
    public User signIn(LoginParam param){
        UserDAO dao = new UserDAO();
        TokenDAO tokenDao = new TokenDAO();

        authenticate(param.getPasswd(),param.getId());
        User user = dao.getUser(param);
        if (user!=null){
            UserSecurity userSecurity = tokenDao.getUserAuthentication(param.getId());
            user.setToken(userSecurity.getToken());
        }

        return user;
    } */

    public boolean signOut(String id){
        StringBuilder query = new StringBuilder("DELETE FROM token WHERE id=?" );
        Connection con = conn.getConnection();
        PreparedStatement ptmt = null;
        try {
            ptmt = con.prepareStatement(query.toString());
            ptmt.setString(1,id);
            ptmt.executeUpdate();
        }catch (Exception e){
            logger.debug(e.getMessage());
            return false;
        } finally {
            try{
                ptmt.close();
                con.close();
            }catch (SQLException e){
                logger.debug(e.getMessage());
            }
        }

        return true;
    }
}
