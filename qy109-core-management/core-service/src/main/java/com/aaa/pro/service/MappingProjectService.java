package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.MappingProjectMapper;
import com.aaa.pro.model.Dict;
import com.aaa.pro.model.MappingProject;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 9:40
 * @Description 项目信息
 **/
@Service
public class MappingProjectService extends BaseService<MappingProject> {

    @Autowired
    private MappingProjectMapper mappingProjectMapper;

    /**
     * @Author zyb
     * @Description 分页查询所需字段数据(项目信息)
     * @Date 2020/7/17 10:11
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    public PageInfo<MappingProject> selectProjectInfoByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingProject> mappingProjects = mappingProjectMapper.selectProjectInfoByPage();
        if (null != mappingProjects && StringUtils.isNotEmpty(String.valueOf(mappingProjects))) {
            return new PageInfo<>(mappingProjects);
        } else {
            return null;
        }
    }

    /**
     * @Author zyb
     * @Description 通过id查询项目信息一行数据
     * @Date 2020/7/17 10:54
     * @Param [id]
     * @Return com.aaa.pro.model.MappingProject
     **/
    public MappingProject selectProjectInfoById(Long id) {
        MappingProject mappingProject = mappingProjectMapper.selectByPrimaryKey(id);
        return null != mappingProject && StringUtils.isNotEmpty(String.valueOf(mappingProject)) ? mappingProject : null;
    }

    /**
     * @Author zyb
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 11:45
     * @Param [id]
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    public List<Map<String, Object>> selectProjectAuditInfoByMappingProjectTableId(Long id) {
        List<Map<String, Object>> mapList = mappingProjectMapper.selectProjectAuditInfoByMappingProjectTableId(id);
        return null != mapList && StringUtils.isNotEmpty(String.valueOf(mapList)) ? mapList : null;
    }

    /**
     * @Author zyb
     * @Description 通过项目名称模糊查询项目信息
     * @Date 2020/7/17 15:08
     * @Param [projectName]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    public List<MappingProject> fuzzySelectProjectInfoByProjectName(String projectName) {
        List<MappingProject> mappingProjects = mappingProjectMapper.fuzzySelectProjectInfoByProjectName(projectName);
        return StringUtils.isNotEmpty(String.valueOf(mappingProjects)) && mappingProjects.size() > 0 ?
                mappingProjects : null;
    }

}
