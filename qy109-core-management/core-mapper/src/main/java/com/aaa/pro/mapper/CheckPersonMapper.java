package com.aaa.pro.mapper;

import com.aaa.pro.model.CheckPerson;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/20 14:31
 * @Description
 **/
public interface CheckPersonMapper extends Mapper<CheckPerson> {

    /**
     * @Author zyb
     * @Description 根据抽查比例分页查询抽查人员信息
     * @Date 2020/7/20 17:00
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.CheckPerson>
     **/
    List<CheckPerson> selectByRatioPage();

    /**
     * @Author zyb
     * @Description 新增抽查人员信息
     * @Date 2020/7/20 18:12
     * @Param [checkPerson]
     * @Return Boolean
     **/
    Boolean addCheckPersonInfo(CheckPerson checkPerson);
}