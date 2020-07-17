package com.aaa.pro.mapper;

import com.aaa.pro.model.Dict;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/16 10:02
 * @Description
 **/
public interface DictMapper extends Mapper<Dict> {

    /**
     * @Author zyb
     * @Description fuzzy query test
     * @Date 2020/7/16 20:53
     * @Param [tableName]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    List<Dict> fuzzy2selectDictByTableName(String tableName);

}