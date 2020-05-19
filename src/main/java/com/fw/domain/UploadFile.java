package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yqf
 * @date 2020 上午10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile implements Serializable {

    private Integer id;

    private String fileName;

    private String fileDownloadUrl;

    private String fileAccessUrl;

    private String fileType;

    private long size;

    private Integer entryId;

    public UploadFile(String fileName, String fileDownloadUrl, String fileAccessUrl, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUrl = fileDownloadUrl;
        this.fileAccessUrl = fileAccessUrl;
        this.fileType = fileType;
        this.size = size;
    }
}
