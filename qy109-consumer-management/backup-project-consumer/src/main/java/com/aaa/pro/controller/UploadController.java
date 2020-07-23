package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zyb
 * @Date Create in 2020/7/22 21:04
 * @Description 单文件上传并添加自动添加日志信息
 **/
@RestController
@Api(tags = "文件上传的接口")
public class UploadController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author zyb
     * @Description 单文件上传并添加自动添加日志信息
     * @Date 2020/7/22 21:03
     * @Param [file]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping(value = "/upload")
    @ApiOperation(value = "单文件上传并添加自动添加日志信息")
    public ResultData uploadFile(MultipartFile file) {
        return iProjectService.uploadFile(file);
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
    @ApiOperation(value = "单文件上传并添加自动添加日志信息(自定义文件名)")
    public ResultData uploadWithCustomFileName(MultipartFile file,
                                               @RequestParam("customFileName") String customFileName) {
        return iProjectService.uploadWithCustomFileName(file, customFileName);
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
    @ApiOperation(value = "文件上传，自定义文件路径和新的文件名")
    public ResultData uploadWithCustomPathAndName(MultipartFile file,
                                                  String filePath,
                                                  String customFileName) {
        return iProjectService.uploadWithCustomPathAndName(file, filePath, customFileName);
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
    @ApiOperation(value = "文件上传，自定义文件上传路径")
    public ResultData uploadWithCustomFilePath(MultipartFile file,
                                               String customFilePath) {
        return iProjectService.uploadWithCustomFilePath(file, customFilePath);
    }

}
