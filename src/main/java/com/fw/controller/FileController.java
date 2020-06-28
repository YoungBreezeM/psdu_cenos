package com.fw.controller;


import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.domain.UploadFile;

import com.fw.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yqf
 * @date 2020 上午10:43
 */
@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    FileService fileService;

    /**
     * 单文件上传
     * */
    @PostMapping("/uploadFile")
    public ResponseEntity<Result> uploadFile(@RequestParam("file") MultipartFile file){

        System.out.println(file);
        return new ResponseEntity<>(fileService.storeFile(file), HttpStatus.OK);

    }
    /**
     *多文件上传
     * 基于单文件上传,通过遍历上传
     * */
    @PostMapping("/uploadMultipleFiles")
    public List<ResponseEntity> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    /**
     * 下载文件
     * */
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // 设置文件传输方式
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * 文件删除
     * */
    @DeleteMapping("deleteFile/{id}")
    public ResponseEntity<Result> deleteFile(@PathVariable Integer id){
        try {
            fileService.deleteFile(id);
            return new ResponseEntity<>(new Result(ResultType.DeleteFileSuccess),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Result(1,e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
