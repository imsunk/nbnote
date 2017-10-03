package com.nbnote.service;


import com.nbnote.conf.Configuration;
import com.nbnote.model.UploadPicture;
import com.nbnote.util.PicutreRename;
import com.nbnote.util.UploadRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.Validate;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by K on 2017. 7. 9..
 */
public class UploadPictureService {
    private static Configuration conf = Configuration.getInstance();
	public UploadPicture uploadNotePicture(HttpServletRequest request) throws Exception {
        UploadRequest req = new UploadRequest(request, conf.IMAGE_TEMP_DIR, 20971520, new PicutreRename());
        Validate.notNull(req.getFile("fileUpload"));
        String filePath = req.getFile("fileUpload").getAbsolutePath();

        UploadPicture result = new UploadPicture();
        result.setFilename(FilenameUtils.getName(filePath));
        result.setFileUrl(conf.getConf(conf.IMAGE_TEMP_DIR+ File.separator+FilenameUtils.getName(filePath)));
        //result.setFileUrl(conf.userUploadTmpUrl(FilenameUtils.getName(filePath)));

        return result;
	}
}