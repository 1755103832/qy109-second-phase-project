package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * @Author zyb
     * @Description 单文件上传并添加自动添加日志信息(自定义文件名)
     * @Date 2020/7/23 16:59
     * @Param [file, customFileName]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping(value = "/uploadWithCustomFileName",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultData uploadWithCustomFileName(MultipartFile file,
                                               @RequestParam("customFileName") String customFileName) {
        Boolean aBoolean = uploadService.upload(file, customFileName);
        return aBoolean ? super.uploadSuccess(file + customFileName) : super.updateFailed();
    }

    /**
     * @Author zyb
     * @Description 文件上传，自定义文件路径和新的文件名
     * @Date 2020/7/23 17:54
     * @Param [file, filePath, customFileName]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping(value = "/uploadWithCustomPathAndName",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultData uploadWithCustomPathAndName(MultipartFile file,
                                                  @RequestParam("filePath") String filePath,
                                                  @RequestParam("customFileName") String customFileName) {
        Boolean aBoolean = uploadService.upload(file, filePath, customFileName);
        return aBoolean ? super.uploadSuccess(file + filePath + customFileName) : super.updateFailed();
    }

    /**
     * @Author zyb
     * @Description 文件上传, 自定义文件上传路径
     * @Date 2020/7/23 18:11
     * @Param [file, customFilePath]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping(value = "/uploadWithCustomFilePath",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultData uploadWithCustomFilePath(MultipartFile file,
                                               @RequestParam("customFilePath") String customFilePath) {
        Boolean aBoolean = uploadService.uploadWithCustomFilePath(file, customFilePath);
        return aBoolean ? super.uploadSuccess(file + customFilePath) : super.updateFailed();
    }

}
