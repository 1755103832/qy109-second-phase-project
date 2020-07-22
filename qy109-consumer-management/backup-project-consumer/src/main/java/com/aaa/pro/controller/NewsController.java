package com.aaa.pro.controller;

import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.News;
import com.aaa.pro.service.IProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

;

/**
 * @ProjectName: qy109-second-phase-project
 * @Package: com.aaa.pro.controller
 * @ClassName: NewsController
 * @Author: jkm
 * @Description:
 * @Date: 2020/7/20 20:34
 * @Version: 1.0
 */
@RestController
public class NewsController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Description 公告栏信息
     * @Author jkm
     * @Date 2020/7/20 20:35
     */
    @PostMapping("/newsPostPageInfo")
    public PageInfo<News> newsPostPageInfo(News news, Integer pageNo, Integer pageSize) {
        return iProjectService.newsPostPageInfo(news, pageNo, pageSize);
    }

    /**
     * @Description 根据id 删除公告
     * @Author jkm
     * @Date 2020/7/21 17:31
     */
    @PostMapping("/newsDelete")
    public Integer newsDelete(@RequestBody News news) {
        return iProjectService.newsDelete(news);
    }

    /**
     * @Description 根据id 批量删除公告
     * @Author jkm
     * @Date 2020/7/21 17:32
     */
    @PostMapping("/newsDeleteByIds")
    public Integer newsDeleteByIds(@RequestParam("ids[]") List<Integer> ids) {
        return iProjectService.newsDeleteByIds(ids);
    }


    /**
     * @Description 修改公告
     * @Author jkm
     * @Date 2020/7/21 17:33
     */
    @PostMapping("/newsUpdate")
    public Integer newsUpdate(@RequestBody News news) {
        return iProjectService.newsUpdate(news);
    }

    /**
     * @Description 添加公告
     * @Author jkm
     * @Date 2020/7/21 19:12
     */
    @PostMapping("/newsAdd")
    public Integer newsAdd(@RequestBody News news) {
        return iProjectService.newsAdd(news);
    }

    /**
     * @Description 根据标题分页模糊 公告栏查询
     * @Author jkm
     * @Date 2020/7/21 14:49
     */
    @PostMapping("/newsFuzzyQuery")
    public ResultData newsFuzzyQuery(@RequestParam("title") String title, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return iProjectService.newsFuzzyQuery(title, pageNo, pageSize);
    }


}
