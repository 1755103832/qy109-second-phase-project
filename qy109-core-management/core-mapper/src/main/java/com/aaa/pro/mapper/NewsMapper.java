package com.aaa.pro.mapper;

import com.aaa.pro.model.News;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: jkm
 * @Description: 新闻公告
 */
public interface NewsMapper extends Mapper<News> {
    /**
     * 根据标题分页模糊 公告栏查询
     *
     * @param title
     * @return
     */
    List<News> newsFuzzyQuery(String title);
}