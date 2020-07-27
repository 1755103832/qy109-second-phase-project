package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Principal;
import com.aaa.pro.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 *    测绘管理--单位负责人
 * @params:
 * @return:
 * @author: Wen
 * @date: 2020/7/17 22:47
 */
@RestController
public class PrincipalController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @description:
     *  查询负责人信息
     * @params: [userId]
     * @return: com.aaa.pro.base.ResultData
     * @author: Wen
     * @date: 2020/7/17 22:38
     */
    @PostMapping("/queryPrincipal")
    public ResultData queryOne(@RequestParam("userId") Long userId) {

        List<Principal> principals = iProjectService.queryOne(userId);
        if (null != principals) {
            return querySuccess(principals);
        }
        return queryFailed();
    }


    /**
     * @description:
     *   修改负责人信息
     * @params: [principal]
     * @return: com.aaa.pro.base.ResultData
     * @author: Wen
     * @date: 2020/7/17 22:34
     */
    @PostMapping("/updateList")
    public ResultData updateList(@RequestBody Principal principal){
        //判断返回的Boolean
        if (iProjectService.updateList(principal)){
            return updateSuccess();
        }
        return updateFailed();
    }


}