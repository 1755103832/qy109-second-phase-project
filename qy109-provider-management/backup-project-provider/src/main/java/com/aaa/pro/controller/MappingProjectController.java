package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.MappingProject;
import com.aaa.pro.service.MappingProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 10:21
 * @Description
 **/
@RestController
public class MappingProjectController extends CommonController<MappingProject> {

    @Autowired
    private MappingProjectService mappingProjectService;

    /**
     * getBaseService
     *
     * @return
     */
    @Override
    public BaseService<MappingProject> getBaseService() {
        return mappingProjectService;
    }

    /**
     * @Author zyb
     * @Description 分页查询所需字段数据(项目信息)
     * @Date 2020/7/17 10:11
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/selectProjectInfoByPage")
    public PageInfo<MappingProject> selectProjectInfoByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return mappingProjectService.selectProjectInfoByPage(pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 通过id查询项目信息一行数据
     * @Date 2020/7/17 10:54
     * @Param [id]
     * @Return com.aaa.pro.model.MappingProject
     **/
    @GetMapping("/selectProjectInfoById")
    public MappingProject selectProjectInfoById(@RequestParam("id") Long id) {
        return mappingProjectService.selectProjectInfoById(id);
    }

    /**
     * @Author zyb
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 11:45
     * @Param [id]
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    @GetMapping("/selectProjectAuditInfoByMappingProjectTableId")
    public PageInfo selectProjectAuditInfoByMappingProjectTableId(@RequestParam("id") Long id,
                                                                  @RequestParam("pageNum") Integer pageNum,
                                                                  @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> mapList = mappingProjectService.selectProjectAuditInfoByMappingProjectTableId(id);
        return mapList.size() > 0 ? new PageInfo(mapList) : null;
    }

    /**
     * @Author zyb
     * @Description 通过项目名称模糊查询项目信息
     * @Date 2020/7/17 15:08
     * @Param [projectName]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/fuzzySelectProjectInfoByProjectName")
    public PageInfo<MappingProject> fuzzySelectProjectInfoByProjectName(@RequestParam("projectName") String projectName,
                                                                        @RequestParam("pageNum") Integer pageNum,
                                                                        @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingProject> mappingProjects = mappingProjectService.fuzzySelectProjectInfoByProjectName(projectName);
        return mappingProjects.size() > 0 ? new PageInfo<>(mappingProjects) : null;

    }

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->分页查询所需字段数据
     * @Date 2020/7/17 16:12
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/huiJiaoResultsInfoByPage")
    public PageInfo<MappingProject> huiJiaoResultsInfoByPage(@RequestParam("pageNum") Integer pageNum,
                                                             @RequestParam("pageSize") Integer pageSize) {
        return mappingProjectService.huiJiaoResultsInfoByPage(pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->根据项目名称分页模糊查询汇交成果信息
     * @Date 2020/7/17 17:13
     * @Param [projectName, pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/fuzzyQueryHuiJiaoByPage")
    public PageInfo<MappingProject> fuzzyQueryHuiJiaoByPage(@RequestParam("projectName") String projectName,
                                                            @RequestParam("pageNum") Integer pageNum,
                                                            @RequestParam("pageSize") Integer pageSize) {
        return mappingProjectService.fuzzyQueryHuiJiaoByPage(projectName, pageNum, pageSize);
    }

}
