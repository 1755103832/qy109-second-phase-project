package com.aaa.pro.mapper;

import com.aaa.pro.model.MappingProject;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 9:39
 * @Description
 **/
public interface MappingProjectMapper extends Mapper<MappingProject> {

    /**
     * @Author zyb
     * @Description 分页查询所需字段数据(项目信息)
     * @Date 2020/7/17 10:11
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    List<MappingProject> selectProjectInfoByPage();

    /**
     * @Author zyb
     * @Description 通过项目名称模糊查询项目信息
     * @Date 2020/7/17 15:08
     * @Param [projectName]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    List<MappingProject> fuzzySelectProjectInfoByProjectName(@Param("projectName") String projectName);

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->分页查询所需字段数据
     * @Date 2020/7/17 16:12
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    List<MappingProject> huiJiaoResultsInfoByPage();

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->根据项目名称分页模糊查询汇交成果信息
     * @Date 2020/7/17 16:42
     * @Param [projectName]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    List<MappingProject> fuzzyQueryHuiJiaoByPage(@Param("projectName") String projectName);

    /**
     * @Author zyb
     * @Description 项目审核-->汇交成果信息-->操作-->查看按钮(项目信息+附件)
     * @Date 2020/7/18 11:29
     * @Param [id]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     **/
    List<Map<String, Object>> queryProjectInfoById(Long id);

    /**
     * @Author zyb
     * @Description 项目审核-->汇交成果信息-->操作-->查看按钮(汇交结果+附件)
     * @Date 2020/7/18 11:46
     * @Param [id]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     **/
    List<Map<String, Object>> queryHuiJiaoResultsById(Long id);
}