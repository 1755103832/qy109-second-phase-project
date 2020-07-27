package com.aaa.pro.mapper;

import com.aaa.pro.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description:
 *   测绘管理--单位信息
 * @author: Wen
 * @date: 2020/7/27 19:38
 */
public interface MappingUnitMapper extends Mapper<MappingUnit> {

    /**
     * @description:
     *   分页查询单位信息
     * @params: []
     * @return: java.util.List<com.aaa.pro.model.MappingProject>
     * @author: Wen
     * @date: 2020/7/22 10:15
     */
    List<MappingUnit> selectUnitInfoByPage();

    /**
     * @description:
     *   通过项目名称模糊查询项目信息
     * @params: [unitName]
     * @return: java.util.List<com.aaa.pro.model.MappingUnit>
     * @author: Wen
     * @date: 2020/7/22 10:53
     */
    List<MappingUnit> fuzzySelectUnitInfoByUnitName(@Param("unitName") String unitName);

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