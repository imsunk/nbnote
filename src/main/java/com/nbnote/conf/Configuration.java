package com.nbnote.conf;

import com.nbnote.log.LoggerLoader;
import jdk.internal.util.xml.impl.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by K on 2017. 6. 15..
 */
public class Configuration {
    private static Logger LOG = LoggerFactory.getLogger(Configuration.class);
    private static final String CONF_FILE = "nbnote.conf";
    public static final String LOG_CONF_FILE = "log4j.conf";
    private static Properties properties = new Properties();
    private static Configuration instance = null;
    /*
    private static class LazyHolder {
        private static final Configuration INSTANCE = new Configuration();
    }
    */
    private Configuration(){
        load();
    }

    public static Configuration getInstance() {
        //return LazyHolder.INSTANCE;
        if (instance == null){
            return new Configuration();
        }

        return instance;
    }

    public void load(){
        try {
            InputStream stream =  new FileInputStream("conf/"+Configuration.CONF_FILE);
            properties.load(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConf(String key){
        return properties.getProperty(key);
    }
}
