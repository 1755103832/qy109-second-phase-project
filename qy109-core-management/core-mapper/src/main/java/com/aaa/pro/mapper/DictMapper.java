package com.aaa.pro.mapper;

import com.aaa.pro.model.Dict;
import org.apache.ibatis.annotations.Param;
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
    List<Dict> fuzzy2selectDictByTableName(@Param("tableName") String tableName);

    /**
     * @Author zyb
     * @Description 分页查询字典表
     * @Date 2020/7/18 16:11
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    List<Dict> queryDictByPage();

    /**
     * @Author zyb
     * @Description 通过key分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    List<Dict> queryDictByKeyPage(Long keyy);

    /**
     * @Author zyb
     * @Description 通过value分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    List<Dict> queryDictByValuePage(String valuee);

    /**
     * @Author zyb
     * @Description 通过字段名分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    List<Dict> queryDictByFieldNamePage(String fieldName);
}