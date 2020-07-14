package com.aaa.pro.controller;

import com.aaa.pro.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 20:11 2020/7/14
 * @description：
 *      文件上传
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean uploadFile(@RequestBody MultipartFile file){
        return uploadService.upload(file);
    }

}
