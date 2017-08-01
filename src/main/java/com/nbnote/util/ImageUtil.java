package com.nbnote.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by K on 2017. 7. 1..
 */
public class ImageUtil {
    public static boolean makeThumbnail(InputStream sourcePath, String targetPath){
        File thumbnail = new File(targetPath);
        try {
            Thumbnails.of(sourcePath).size(190, 150).outputFormat("png").toFile(thumbnail);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void moveImageToTemp(String path){
    	// TODO Auto-generated method stub
    }

    public static void deleteTempFile(String path){
    	// TODO Auto-generated method stub
    }

    public static void copyImageTo(String sourcePath,String target){
    	// TODO Auto-generated method stub
    }

	public static String makeThumbnail(String absolutePath) {
		// TODO Auto-generated method stub
		return null;
	}
}
