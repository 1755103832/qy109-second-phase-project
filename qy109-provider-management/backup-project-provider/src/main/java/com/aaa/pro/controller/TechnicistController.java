package com.aaa.pro.controller;

import com.aaa.pro.model.Technicist;
import com.aaa.pro.service.TechnicistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 *    测绘管理-单位基本信息-技术人员信息
 * @params:
 * @return:
 * @author: Wen
 * @date: 2020/7/20 10:04
 */
@RestController
public class TechnicistController {

    @Autowired
    private TechnicistService technicistService;

    /**
     * @description:
     *   查询技术人员信息
     * @params: [userId]
     * @return: java.util.List<com.aaa.pro.model.Technicist>
     * @author: Wen
     * @date: 2020/7/18 16:23
     */
    @PostMapping("/queryTechnicist")
    public List<Technicist> queryTechnicist(@RequestParam("userId") Long userId){
        List<Technicist> technicist = technicistService.queryTechnicist(userId);
        if (null != technicist){
            return technicist;
        }
        return null;
    }

   /**
    * @description:
    *   修改技术人员信息
    * @params: [technicist]
    * @return: java.lang.Boolean
    * @author: Wen
    * @date: 2020/7/18 16:24
    */
    @PostMapping("/updateTechnicist")
    public Boolean updateTechnicist(@RequestBody Technicist technicist){
        return technicistService.updataTechnicist(technicist);
    }


}
