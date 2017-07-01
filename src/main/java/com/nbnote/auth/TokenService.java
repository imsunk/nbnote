package com.nbnote.auth;

import com.nbnote.db.DbHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.CachedRowSet;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by K on 2017. 6. 18..
 */
public class TokenService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Connection conn = DbHandler.getInstance().getConnection();
    private PreparedStatement ptmt;
    private ResultSet rs;

    public TokenService() {
    }

    public Token createToken(String userId,String ip, String platform) throws Exception {
        Token token = new Token();
        Date now = new Date();

        token.setToken(Token.generateToken());
        token.setExpireDate(generateExpireDate(now));
        token.setGenDate(now);
        token.setUserId(userId);
        token.setPlatform(platform);
        token.setIp(ip);

        insertToken(token);

        return token;
    }

    private Date generateExpireDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, Token.DEFAULT_EXPIRE_DATE);
        return calendar.getTime();
    }
    /**
     * 토큰 만기시간 연장
     * @param tokenId
     * @throws Exception
     */
    public void extendExpireDate(String tokenId) throws Exception {
        Date expireDate = generateExpireDate(new Date());
        StringBuilder query = new StringBuilder("UPDATE token SET expire_date = ? where token= ?");
       //update("UPDATE token SET expire_date = ? WHERE token = ?", date2String(expireDate), tokenId);
        ptmt = conn.prepareStatement(query.toString());
        ptmt.setString(1,date2String(expireDate));
        ptmt.setString(2,tokenId);
        ptmt.executeQuery();

        ptmt.close();
    }


    private void insertToken(Token token) throws Exception {
        StringBuilder query = new StringBuilder("INSERT INTO token (id, token, expire_date, gen_date, platform, ip) values (?,?,?,?,?,?)");
        ptmt = conn.prepareStatement(query.toString());
        ptmt.setString(1,token.getUserId());
        ptmt.setString(2,token.getToken());
        ptmt.setTimestamp(3, new java.sql.Timestamp(token.getExpireDate().getTime()));
        ptmt.setTimestamp(4, new java.sql.Timestamp(token.getGenDate().getTime()));
        ptmt.setString(5,token.getPlatform());
        ptmt.setString(6,token.getIp());
        ptmt.executeUpdate();

        ptmt.close();
    }

    public Token getToken(String id) throws Exception {
        Date now = new Date();
        StringBuilder query = new StringBuilder("SELECT token, user_id, expire_date, gen_date, ip, platform FROM token WHERE id = ?");
        ptmt = conn.prepareStatement(query.toString());
        ptmt.setString(1,id);
        rs = ptmt.executeQuery();
        rs.next();
        Token token = new Token();
        token.setToken(rs.getString(1));
        token.setUserId(rs.getString(2));
        token.setExpireDate(string2Date(rs.getString(3)));
        token.setGenDate(string2Date(rs.getString(4)));
        token.setIp(rs.getString(5));
        token.setPlatform(rs.getString(6));

        ptmt.close();
        rs.close();

        return token;

    }
    public void deleteToken(String token) throws Exception {
        StringBuilder query = new StringBuilder("DELETE FROM token WHERE token = ?");
        ptmt = conn.prepareStatement(query.toString());
        ptmt.setString(1,token);
        ptmt.executeQuery();

        ptmt.close();

    }
    private String date2String(Date date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
        return formatter.format(date);
    }

    //format: yyyy-MM-dd HH:mm
    public Date string2Date(String date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(date);
    }
}
