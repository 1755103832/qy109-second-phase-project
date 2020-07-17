package com.aaa.pro.controller;

import com.aaa.pro.model.Principal;
import com.aaa.pro.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 *    测绘管理-单位基本信息-负责人信息
 * @params:
 * @return:
 * @author: Wen
 * @date: 2020/7/17 22:44
 */
@RestController
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;

   /**
    * @description:
    *   查询负责人信息
    * @params: [userId]
    * @return: java.util.List<com.aaa.pro.model.Principal>
    * @author: Wen
    * @date: 2020/7/17 22:44
    */
    @PostMapping("/queryPrincipal")
    public List<Principal> qureyOne(@RequestParam("userId") Long userId){
        List<Principal> principals = principalService.queryOne(userId);
        if (null != principals){
            return principals;
        }
        return null;
    }

    /**
     * @description:
     *   修改负责人信息
     * @params: [principal]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/17 22:45
     */
    @PostMapping("/updateList")
    public Boolean updateList(@RequestBody Principal principal){
        return principalService.updateList(principal);
    }


}
