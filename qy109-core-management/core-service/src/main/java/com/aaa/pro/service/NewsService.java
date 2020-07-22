package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.NewsMapper;
import com.aaa.pro.model.News;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName: qy109-second-phase-project
 * @Package: com.aaa.pro.service
 * @ClassName: NewsService
 * @Author: jkm
 * @Description:
 * @Date: 2020/7/20 20:28
 * @Version: 1.0
 */
@Service
public class NewsService extends BaseService<News> {

    @Autowired
    private NewsMapper newsMapper;

    /**
     * @Description 根据标题分页模糊 公告栏查询
     * @Author jkm
     * @Date 2020/7/21 14:38
     */
    public PageInfo<News> newsFuzzyQuery(@RequestParam("title") String title, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<News> resultList = newsMapper.newsFuzzyQuery(title);
        PageInfo<News> pageInfo = new PageInfo<News>(resultList);
        return pageInfo;
    }
}
