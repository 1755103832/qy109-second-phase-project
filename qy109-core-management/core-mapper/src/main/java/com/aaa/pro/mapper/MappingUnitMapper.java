package com.aaa.pro.mapper;

import com.aaa.pro.model.MappingUnit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/18 18:48
 * @Description
 **/
public interface MappingUnitMapper extends Mapper<MappingUnit> {

    /**
     * @Author zyb
     * @Description 查询白名单人员信息
     * @Date 2020/7/18 18:51
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingUnit>
     **/
    List<MappingUnit> selectByUnitStatus();

    /**
     * @Author zyb
     * @Description 查询黑名单人员信息
     * @Date 2020/7/18 19:04
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingUnit>
     **/
    List<MappingUnit> selectByUnitStatus2();

    /**
     * @Author zyb
     * @Description 根据抽查比例查询单位信息
     * @Date 2020/7/19 9:02
     * @Param [ownedDistrict]
     * @Return java.util.List<com.aaa.pro.model.MappingUnit>
     **/
    List<MappingUnit> selectByRatioAndType(String ownedDistrict);
}