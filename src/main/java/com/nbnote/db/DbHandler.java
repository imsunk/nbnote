package com.nbnote.db;

/**
 * Created by K on 2017. 6. 15..
 */

import com.nbnote.conf.Configuration;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbHandler {
    private static DbHandler instance;
    private BasicDataSource bds;

    private DbHandler() {
        Configuration conf = Configuration.getInstance();

        bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl(conf.getConf(conf.DB_HOST));
        bds.setUsername(conf.getConf(conf.DB_USER));
        bds.setPassword(conf.getConf(conf.DB_PASSWD));
        bds.setValidationQuery("SELECT 1");     //isValid오류 방지
        bds.setTestOnBorrow(false);             //pool에 넣을 때 검증 여부
        bds.setPoolPreparedStatements(true);    //쿼리 파싱부하 감소
    }

    public static DbHandler getInstance() {
        if (instance == null) {
            synchronized (DbHandler.class) {
                instance = new DbHandler();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return bds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}