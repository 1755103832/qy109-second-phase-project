package com.aaa.pro.mapper;


import com.aaa.pro.model.Score;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description:
 *    单位审核--单位分值信息
 * @author: Wen
 * @date: 2020/7/21 19:26
 */
public interface ScoreMapper extends Mapper<Score> {
    /**
     * 根据单位编号，查询单位分值信息
     * @param unitid
     * @return
     */
    List<Score> selectScoreByRefId(Integer unitid);
}