package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author zyb
 * @Date Create in 2020/7/22 20:59
 * @Description 单文件上传并添加自动添加日志信息
 **/
@RestController
public class UploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    /**
     * @Author zyb
     * @Description 单文件上传并添加自动添加日志信息
     * @Date 2020/7/22 21:03
     * @Param [file]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultData uploadFile(MultipartFile file) {
        Boolean aBoolean = uploadService.upload(file);
        return aBoolean ? super.uploadSuccess(file) : super.updateFailed();
    }

}
