package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.News;
import com.aaa.pro.service.NewsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ProjectName: qy109-second-phase-project
 * @Package: com.aaa.cloud.controller
 * @ClassName: NewsController
 * @Author: jkm
 * @Description:
 * @Date: 2020/7/20 20:29
 * @Version: 1.0
 */
@RestController
public class NewsController extends CommonController<News> {

    @Autowired
    private NewsService newsService;

    @Override
    public BaseService<News> getBaseService() {
        return newsService;
    }

    /**
     * @Description 公告栏信息
     * @Author jkm
     * @Date 2020/7/20 20:31
     */
    @PostMapping("/newsPostPageInfo")
    public PageInfo<News> newsPostPageInfo(News news,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize) {
        PageInfo<News> resultList = getBaseService().selectListByPage(news, pageNo, pageSize);
        if (null != resultList && resultList.getSize() > 0) {
            return resultList;
        }
        return null;
    }


    /**
     * @Description 根据id 删除公告
     * @Author jkm
     * @Date 2020/7/21 15:22
     */
    @PostMapping("/newsDelete")
    public Integer newsDelete(News news) {
        Integer integer = newsService.deleteByPrimaryKey(news);
        if (integer > 0) {
            return integer;
        }
        return null;
    }

    /**
     * @Description 根据id 批量删除公告
     * @Author jkm
     * @Date 2020/7/21 15:32
     */
    @PostMapping("/newsDeleteByIds")
    public Integer newsDeleteByIds(@RequestParam("ids[]") List<Integer> ids) {
        Integer integer = newsService.batchDeleteNewsByIds(ids);
        if (integer > 0) {
            return integer;
        }
        return null;
    }

    /**
     * @Description 修改公告
     * @Author jkm
     * @Date 2020/7/21 15:41
     */
    @PostMapping("/newsUpdate")
    public Integer newsUpdate(News news) {
        Integer integer = newsService.updateByPrimaryKeySelective(news);
        if (integer > 0) {
            return integer;
        }
        return null;
    }

    /**
     * @Description 添加公告
     * @Author jkm
     * @Date 2020/7/21 15:36
     */
    @PostMapping("/newsAdd")
    public Integer newsAdd(News news) {
        Integer integer = newsService.insertSelective(news);
        if (integer > 0) {
            return integer;
        }
        return null;
    }

    /**
     * @Description 根据标题分页模糊 公告栏查询
     * @Author jkm
     * @Date 2020/7/21 15:00
     */
    @PostMapping("/newsFuzzyQuery")
    public ResultData newsFuzzyQuery(@RequestParam("title") String title, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<News> newsPageInfo = newsService.newsFuzzyQuery(title, pageNo, pageSize);
        if (null != newsPageInfo && newsPageInfo.getSize() > 0) {
            return querySuccess(newsPageInfo);
        }
        return queryFailed();
    }

}
