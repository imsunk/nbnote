package com.nbnote.util;

import com.oreilly.servlet.multipart.FileRenamePolicy;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by K on 2017. 7. 11..
 */
public class UploadRequest {
    private static Logger logger = LoggerFactory.getLogger(UploadRequest.class);

    private class UploadFile {
        String contentType;
        File file;
        String fileName;
        String originalFileName;

        UploadFile(String contentType, File file, String fileName, String originalFileName) {
            this.contentType = contentType;
            this.file = file;
            this.fileName = fileName;	//rename이후 변경된 파일명
            this.originalFileName = originalFileName;
        }

        @Override
        public String toString() {
            return "CosFile [contentType=" + contentType + ", file=" + file + ", fileName=" + fileName
                    + ", originalFileName=" + originalFileName + "]";
        }
    }

    private String saveDir;

    private Map<String, List<String>> formMap = new HashMap<String,List<String>>();
    private Map<String, UploadFile> fileMap = new LinkedHashMap<String, UploadFile>();
    
    private String encodeType = "UTF-8";

    public UploadRequest(HttpServletRequest request, String saveDir, long maxSize, FileRenamePolicy renamePolicy) throws Exception {
        this.saveDir = saveDir;

        if(!ServletFileUpload.isMultipartContent(request)) {
        	logger.info("! isMultipartContent");
        }

        //check boundary header:헤더에 boundary가 없으면 apache common fileupload에서 에러 발생
        if(!request.getHeader("Content-Type").matches(".*; ?boundary=.*")) {
        	logger.info("! hasnot boundary");
        }

        FileUtil.checkMakeDir(saveDir);

        //임시파일 삭제 객체
        DeleteUploadCleaningTracker tracker = new DeleteUploadCleaningTracker();

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setFileCleaningTracker(tracker);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(maxSize);	//11,831

        // Parse the request
        List<FileItem> items = upload.parseRequest(request);

        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();

            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString(encodeType);

                List<String> valueList;
                if(formMap.containsKey(name)) {
                    valueList = formMap.get(name);
                    valueList.add(value);
                }
                else {
                    valueList = new ArrayList<String>();
                    valueList.add(value);
                    formMap.put(name, valueList);
                }
            } else {
                if(item.getName().trim().isEmpty()) continue;

                File renamedFile = renamePolicy.rename(new File(this.saveDir, UploadRequest.getFileName(item.getName())));
                item.write(renamedFile);

                UploadFile uploadFile = new UploadFile(item.getContentType(), renamedFile, renamedFile.getName(), UploadRequest.getFileName(item.getName()));
                fileMap.put(item.getFieldName(), uploadFile);
            }
        }

        //tmp파일 삭제
        tracker.deleteTemporaryFiles();
    }

    public File getFile(String name) {
        if(fileMap.containsKey(name))
            return fileMap.get(name).file;

        return null;
    }
    /*
     * field name반환
     */
    public Enumeration<String> getParameterNames() {
                                                 return Collections.enumeration(fileMap.keySet());
                                                                                                  }

    public static String getFileName(String filename) {
        if (filename != null) {
            return FilenameUtils.getName(filename).replaceAll(" ", "_");
        }else return filename;
    }
}
