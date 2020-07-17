package com.aaa.pro.controller;


import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Mapping_unit;
import com.aaa.pro.service.IProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "单位基本信息", tags = "单位基本信息")
public class BaseInformationController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    @PostMapping("/queryMapping_unit")
    public ResultData queryMapping_unit(@RequestParam("userId") Long userId){
        //根据userID查询单位信息
        List<Mapping_unit> mapping_units = iProjectService.queryMapping_unit(userId);
        //判断查询结果是否为空
        if (null!=mapping_units){
            //如果不为空就返回查询数据
            return super.querySuccess(mapping_units);
        }
        //为空就返回查询失败信息
        return super.queryFailed();
    }


}
