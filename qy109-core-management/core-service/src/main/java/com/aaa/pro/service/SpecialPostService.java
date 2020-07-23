package com.aaa.pro.service;


import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.SpecialPostMapper;
import com.aaa.pro.model.SpecialPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单位审核-特殊岗位人员信息
 */
@Service
public class SpecialPostService extends BaseService<SpecialPost> {

    @Autowired
    private SpecialPostMapper specialPostMapper;

    /**
     * @description: 查询特殊人员信息
     * @params: [userId]
     * @return: java.util.List<com.aaa.pro.model.SpecialPost>
     * @author: Wen
     * @date: 2020/7/20 20:45
     */
    public List<SpecialPost> selectSpecialPost(Long userId) {
        List<SpecialPost> specialPosts = specialPostMapper.selectSpecialPost(userId);
        return specialPosts;
    }
}