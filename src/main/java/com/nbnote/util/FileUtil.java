package com.nbnote.util;

import com.nbnote.conf.Configuration;

import java.io.File;

/**
 * Created by K on 2017. 7. 8..
 */
public class FileUtil {
    private static Configuration conf = Configuration.getInstance();
    public static boolean checkMakeDir(String dir) {
        File f = new File(dir);
        if (!f.exists()) return f.mkdirs();
        else return true;
    }
    public static String userUploadTmpDir(String filename) {
        StringBuffer tempPhotoUrl = new StringBuffer()
                .append(conf.getConf("sys_path"))
                .append(conf.getConf("path"))
                .append(File.separator)
                .append("data")
                .append(File.separator)
                .append("upload_tmp")
                .append(File.separator);

        if(filename != null) tempPhotoUrl.append(filename);

        return tempPhotoUrl.toString();
    }
}
