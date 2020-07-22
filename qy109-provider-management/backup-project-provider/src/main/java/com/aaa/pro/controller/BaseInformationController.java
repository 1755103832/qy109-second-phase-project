package com.aaa.pro.controller;

import com.aaa.pro.model.Mapping_unit;
import com.aaa.pro.service.BaseInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 测绘管理--单位审核
 * @params:
 * @return:
 * @author: Wen
 * @date: 2020/7/17 16:26
 */
@RestController
public class BaseInformationController {
    @Autowired
    private BaseInfomationService baseInfomationService;
    @PostMapping("/queryMapping_unit")
    List<Mapping_unit> queryMapping_unit(@RequestParam("userId") Long userId){
        List<Mapping_unit> mapping_units = baseInfomationService.queryMapping_unit(userId);
        if (null!=mapping_units){
            return mapping_units;
        }
        return null;
    }

}
