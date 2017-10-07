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
	final static String UPLOAD_FILE_FORM_NAME = "fileUpload";
	
	public UploadPicture uploadNotePicture(HttpServletRequest request) throws Exception {

		UploadRequest req = new UploadRequest(request, Configuration.getConf(Configuration.IMAGE_TEMP_DIR), 20971520,
				new PicutreRename());

		Validate.notNull(req.getFile(UPLOAD_FILE_FORM_NAME));
		String filePath = req.getFile(UPLOAD_FILE_FORM_NAME).getAbsolutePath();
		UploadPicture result = new UploadPicture();

		result.setFilename(FilenameUtils.getName(filePath));

		return result;
	}
}