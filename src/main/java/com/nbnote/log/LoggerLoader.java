package com.nbnote.log;

import com.nbnote.conf.Configuration;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by K on 2017. 6. 15..
 */
public class LoggerLoader {

    private static Logger LOG = LoggerFactory.getLogger(LoggerLoader.class);

    public static void load(){
        Properties logProperties = new Properties();
        InputStream stream = null;
        try {
            //stream = LoggerLoader.class.getClassLoader().getResourceAsStream("conf/"+Configuration.LOG_CONF_FILE);
            stream = new FileInputStream("conf/"+Configuration.LOG_CONF_FILE);
            logProperties.load(stream);
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }finally{
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        PropertyConfigurator.configure(logProperties);
    }

}
