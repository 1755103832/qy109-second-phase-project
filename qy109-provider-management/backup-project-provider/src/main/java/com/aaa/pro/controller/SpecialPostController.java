package com.aaa.pro.controller;


import com.aaa.pro.model.SpecialPost;
import com.aaa.pro.service.SpecialPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecialPostController {

    @Autowired
    private SpecialPostService specialPostService;

    /**
     * @description:
     *    查询特殊岗位人员信息
     * @params: [userId]
     * @return: java.util.List<com.aaa.pro.model.SpecialPost>
     * @author: Wen
     * @date: 2020/7/20 21:00
     */
    @PostMapping("/querySpecialPost")
    public List<SpecialPost> selectSpecialPost(@RequestParam("userId") Integer  userId) {

        Long userId1 = new Long(userId);
        return specialPostService.selectSpecialPost(userId1);
    }
}