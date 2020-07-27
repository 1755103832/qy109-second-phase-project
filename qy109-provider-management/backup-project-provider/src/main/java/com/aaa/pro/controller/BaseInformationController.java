package com.aaa.pro.controller;

import com.aaa.pro.model.Mapping_unit;
import com.aaa.pro.service.BaseInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 测绘管理--单位基本信息
 * @params:
 * @return:
 * @author: Wen
 * @date: 2020/7/17 16:26
 */
@RestController
public class BaseInformationController {
    @Autowired
    private BaseInfomationService baseInfomationService;

    /**
     * @description:
     *    查询单位信息（id）
     * @params: [userId]
     * @return: java.util.List<com.aaa.pro.model.Mapping_unit>
     * @author: Wen
     * @date: 2020/7/27 19:41
     */
    @PostMapping("/queryMapping_unit")
    List<Mapping_unit> queryMapping_unit(@RequestParam("userId") Long userId){
        List<Mapping_unit> mapping_units = baseInfomationService.queryMapping_unit(userId);
        if (null!=mapping_units){
            return mapping_units;
        }
        return null;
    }



}
