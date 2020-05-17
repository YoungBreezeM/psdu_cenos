package com.fw.service;


import com.fw.domain.UploadFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author yqf
 * @date 2020 上午11:10
 */
public interface FileService {
    /**
     * 查找所有文件
     * @return list
     * */
    List<UploadFile> findAll();

    /**
     * 通过id 查找文件
     * @param id file id
     * @return uploadFile
     * */
    UploadFile findUploadFileById(Integer id);

    /**
     * 文件上传
     * @param file
     * @return file
     * */
    UploadFile storeFile(MultipartFile file);

    /**
     * 文件下载
     * @param fileName
     * @return resource
     * **/
    Resource loadFileAsResource(String fileName);

    /**
     * 删除文件
     * @param id file id
     * **/
    void deleteFile(Integer id);

}
