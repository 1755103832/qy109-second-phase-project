package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "单文件上传并添加自动添加日志信息")
    public ResultData uploadFile(MultipartFile file) {
        return iProjectService.uploadFile(file);
    }

}
