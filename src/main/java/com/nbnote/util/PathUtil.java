package com.nbnote.util;

import com.nbnote.conf.Configuration;

public class PathUtil {
	public static String userUploadTmpUrl(String name) {
		return Configuration.getConf(Configuration.SERVER_URI_ROOT)+"file/"+name;
	}

	public static String userUploadTmpDir(String filename) {
		
		return "D:/web_app/"+Configuration.getConf(Configuration.IMAGE_TEMP_DIR);
	}
}
