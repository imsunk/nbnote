package com.nbnote.model;

/**
 * Created by K on 2017. 7. 1..
 */
public class UploadPicture {
    private String filename;
    private String fileUrl;
    private String fileThumbUrl;
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getFileThumbUrl() {
        return fileThumbUrl;
    }
    public void setFileThumbUrl(String fileThumbUrl) {
        this.fileThumbUrl = fileThumbUrl;
    }
}
