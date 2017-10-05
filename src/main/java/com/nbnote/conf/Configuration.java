package com.nbnote.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
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

	public static final String DB_HOST = "db.nbnote.host";
	public static final String DB_USER = "db.nbnote.uname";
	public static final String DB_PASSWD = "db.nbnote.passwd";

	public static final String IMAGE_TEMP_DIR = "path.image.upload.temp";
	public static final String IMAGE_ORG_DIR = "path.image.origin";
	public static final String IMAGE_THUMB_DIR = "path.image.thumbnail";
	
	public static final String SERVER_URI_ROOT = "server.uri.root";
	public static final String SERVER_PATH_ROOT = "server.path.root";
	public static final String TEST_UPLOAD_FILE = "test.upload.file";
	
	

	/*
	 * private static class LazyHolder { private static final Configuration
	 * INSTANCE = new Configuration(); }
	 */
	
	static {
		getInstance();
	}
	
	private Configuration() {
		load();
	}

	public static Configuration getInstance() {
		// return LazyHolder.INSTANCE;
		if (instance == null) {
			return new Configuration();
		}

		return instance;
	}

	public void load() {
		try {
			InputStream stream = this.getClass().getResourceAsStream("/" + Configuration.CONF_FILE);
			properties.load(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getConf(String key) {
		return properties.getProperty(key);
	}

	public String userUploadTmpUrl(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
