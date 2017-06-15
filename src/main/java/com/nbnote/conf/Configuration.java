package com.nbnote.conf;

import com.nbnote.log.LoggerLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by K on 2017. 6. 15..
 */
public class Configuration {

    private static Logger LOG = LoggerFactory.getLogger(Configuration.class);
    private static final String CONF_FILE = "config.properties";
    public static final String LOG_CONF_FILE = "log4j.properties";
    private static Properties properties;

    private static class LazyHolder {
        private static final Configuration INSTANCE = new Configuration();
    }
    private Configuration(){
        load();
    }

    public static Configuration getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void load(){
        InputStream stream = Configuration.class.getClassLoader().getResourceAsStream(Configuration.CONF_FILE);
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConf(String key){
        return properties.getProperty(key);
    }
}
