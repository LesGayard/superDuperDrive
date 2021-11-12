package com.udacity.jwdnd.course1.cloudstorage.model;

public class FileModel {

    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userId;
    private byte[] filedata;

    public FileModel() {
    }

    public FileModel(Integer fileId, String fileName, String contenttype, String filesize, Integer userId, byte[] filedata) {
        this.fileId = fileId;
        this.filename = fileName;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userId = userId;
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return this.fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return this.contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return this.filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getFiledata() {
        return this.filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + this.fileId +
                ", fileName='" + this.filename + '\'' +
                ", contentType='" + this.contenttype + '\'' +
                ", fileSize='" + this.filesize + '\'' +
                ", userId=" + this.userId +
                ", fileData=" + this.filedata +
                '}';
    }
}
