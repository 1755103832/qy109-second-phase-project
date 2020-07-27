package com.aaa.pro.controller;


import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.SpecialPost;
import com.aaa.pro.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @description:
 *    测绘管理--特殊岗位人员信息
 * @author: Wen
 * @date: 2020/7/27 10:50
 */
@RestController
public class SpecialPostController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

   /**
    * @description:
    *   查询特殊岗位人员信息
    * @params: [userId]
    * @return: com.aaa.pro.base.ResultData
    * @author: Wen
    * @date: 2020/7/20 21:31
    */
    @PostMapping("/querySpecialPost")
    public ResultData selectSpecialPost(@RequestParam("userId") Long userId){
        if (userId != null) {
            //根据userID查询信息
            List<SpecialPost> specialPosts = iProjectService.selectSpecialPost(userId);
            return querySuccess(specialPosts);
        }
        return queryFailed();
    }
}
