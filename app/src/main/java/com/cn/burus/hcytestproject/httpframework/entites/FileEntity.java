package com.cn.burus.hcytestproject.httpframework.entites;

/**
 * 文件类实体
 * Created by chengyou.huang on 2017/5/11.
 */

public class FileEntity {

    private String fileName;
    private String fileType;
    private String filePath;

    public FileEntity() {
    }

    public FileEntity(String fileName, String fileType, String filePath) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
