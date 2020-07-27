package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Technicist;
import com.aaa.pro.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 *   测绘管理--技术人员信息
 * @params:
 * @return:
 * @author: Wen
 * @date: 2020/7/18 16:12
 */
@RestController
public class TechnicistController extends BaseController {

    @Autowired
    private IProjectService iProjectService;


    /**
     * @description:
     *   查询技术人员信息
     * @params: [UserId]
     * @return: com.aaa.pro.base.ResultData
     * @author: Wen
     * @date: 2020/7/18 16:11
     */
    @PostMapping("/queryTechnicist")
    public ResultData queryTechnicist(@RequestParam("userId") Long UserId){
        //根据userID获取技术人员信息
        List<Technicist> technicists = iProjectService.queryTechnicist(UserId);
        //判断技术人员信息是否为空
        if (null != technicists){
            //不为空就返回带数据的信息
            return querySuccess(technicists);
        }
        return queryFailed();
    }

   /**
    * @description:
    *   修改技术人员信息
    * @params: [technicist]
    * @return: com.aaa.pro.base.ResultData
    * @author: Wen
    * @date: 2020/7/18 16:11
    */
    @PostMapping("/updateTechnicist")
    public ResultData updateTechnicist(@RequestBody Technicist technicist){
        if (iProjectService.updateTechnicist(technicist)){
            //如果为true就返回修改成功信息
            return updateSuccess();
        }
        return updateFailed();
    }

}
