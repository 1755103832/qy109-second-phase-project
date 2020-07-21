package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.MappingProjectMapper;
import com.aaa.pro.model.Dict;
import com.aaa.pro.model.MappingProject;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->分页查询所需字段数据
     * @Date 2020/7/17 16:12
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    public PageInfo<MappingProject> huiJiaoResultsInfoByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingProject> mappingProjects = mappingProjectMapper.huiJiaoResultsInfoByPage();
        return StringUtils.isNotEmpty(String.valueOf(mappingProjects)) && mappingProjects.size() > 0 ?
                new PageInfo<>(mappingProjects) : null;
    }

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->根据项目名称分页模糊查询汇交成果信息
     * @Date 2020/7/17 17:13
     * @Param [projectName, pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    public PageInfo<MappingProject> fuzzyQueryHuiJiaoByPage(String projectName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotEmpty(projectName)) {
            List<MappingProject> list = mappingProjectMapper.fuzzyQueryHuiJiaoByPage(projectName);
            return new PageInfo<>(list);
        } else {
            return null;
        }
    }

    /**
     * @Author zyb
     * @Description 项目审核-->汇交成果信息-->操作-->查看按钮(项目信息+附件)
     * @Date 2020/7/18 11:29
     * @Param [id]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     **/
    public List<Map<String, Object>> queryProjectInfoById(Long id) {
        if (null != id && StringUtils.isNotEmpty(id.toString())) {
            List<Map<String, Object>> map = mappingProjectMapper.queryProjectInfoById(id);
            if (map.size() > 0) {
                return map;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @Author zyb
     * @Description 项目审核-->汇交成果信息-->操作-->查看按钮(汇交结果+附件)
     * @Date 2020/7/18 11:46
     * @Param [id]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     **/
    public List<Map<String, Object>> queryHuiJiaoResultsById(Long id) {
        if (null != id && StringUtils.isNotEmpty(id.toString())) {
            List<Map<String, Object>> map = mappingProjectMapper.queryHuiJiaoResultsById(id);
            if (map.size() > 0) {
                return map;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @Author zyb
     * @Description 项目审核模块(项目审核界面)
     * @Date 2020/7/18 15:29
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingProject>
     **/
    public PageInfo<MappingProject> queryStatus2(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingProject> projects = mappingProjectMapper.queryStatus2();
        return StringUtils.isNotEmpty(projects.toString()) && projects.size() > 0 ?
                new PageInfo<>(projects) : null;
    }

    /**
     * @Author zyb
     * @Description 项目审核模块(项目审核界面 - - > 根据项目名称模糊查询)
     * @Date 2020/7/18 15:41
     * @Param [projectName, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingProject>
     **/
    public PageInfo<MappingProject> fuzzyQueryStatus2(String projectName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingProject> projects = mappingProjectMapper.fuzzyQueryStatus2(projectName);
        return StringUtils.isNotEmpty(projects.toString()) && projects.size() > 0 ?
                new PageInfo<>(projects) : null;
    }

    /**
     * @Author zyb
     * @Description 项目审核模块(成果汇交审核界面 - - > 分页查询)
     * @Date 2020/7/18 15:59
     * @Param []
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingProject>
     **/
    public PageInfo<MappingProject> resultsHuiJiaoAuditByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingProject> projects = mappingProjectMapper.resultsHuiJiaoAuditByPage();
        return StringUtils.isNotEmpty(projects.toString()) && projects.size() > 0 ?
                new PageInfo<>(projects) : null;
    }

    /**
     * @Author zyb
     * @Description 通过项目名称模糊查询+查询所有并分页
     * @Date 2020/7/21 16:09
     * @Param [mappingProject, pageNo, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingProject>
     **/
    public PageInfo<MappingProject> selectFuzzy(MappingProject mappingProject, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        if (null != mappingProject.getProjectName()) {
            // 模糊查询
            Example example = new Example(MappingProject.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("projectName", " %" + mappingProject.getProjectName() + "%");
            List<MappingProject> userList = mappingProjectMapper.selectByExample(example);
            PageInfo<MappingProject> pageInfo = new PageInfo<MappingProject>(userList);
            return pageInfo.getSize() > 0 ? pageInfo : null;

        } else {
            List<MappingProject> mappingProjects = super.selectList(mappingProject);
            PageInfo<MappingProject> pageInfo = new PageInfo<MappingProject>(mappingProjects);
            return pageInfo.getSize() > 0 ? pageInfo : null;
        }

    }
}
