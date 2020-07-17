package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.MappingProject;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 10:23
 * @Description
 **/
@RestController
@Api(tags = "项目信息表接口")
public class MappingProjectController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * @Author zyb
     * @Description 分页查询所需字段数据(项目信息)
     * @Date 2020/7/17 10:11
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/selectProjectInfoByPage")
    @ApiOperation(value = "分页查询所需字段数据")
    public ResultData selectProjectInfoByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> mappingProjectPageInfo = projectService.selectProjectInfoByPage(pageNum, pageSize);
        return null != mappingProjectPageInfo && StringUtils.isNotEmpty(String.valueOf(mappingProjectPageInfo)) ?
                super.querySuccess(mappingProjectPageInfo) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 通过id查询项目信息一行数据
     * @Date 2020/7/17 10:54
     * @Param [id]
     * @Return com.aaa.pro.model.MappingProject
     **/
    @GetMapping("/selectProjectInfoById")
    @ApiOperation(value = "通过id查询项目信息一行数据")
    public ResultData selectProjectInfoById(@RequestParam("id") Long id) {
        MappingProject mappingProject = projectService.selectProjectInfoById(id);
        return null != mappingProject && StringUtils.isNotEmpty(String.valueOf(mappingProject)) ?
                super.querySuccess(mappingProject) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 11:45
     * @Param [id]
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    @GetMapping("/selectProjectAuditInfoByMappingProjectTableId")
    @ApiOperation(value = "通过项目信息id编号查询项目审核记录")
    public ResultData selectProjectAuditInfoByMappingProjectTableId(@RequestParam("id") Long id,
                                                                    @RequestParam("pageNum") Integer pageNum,
                                                                    @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = projectService.selectProjectAuditInfoByMappingProjectTableId(id, pageNum, pageSize);
        return null != pageInfo && StringUtils.isNotEmpty(String.valueOf(pageInfo)) ?
                super.querySuccess(pageInfo) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 通过项目名称模糊查询项目信息
     * @Date 2020/7/17 15:08
     * @Param [projectName]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/fuzzySelectProjectInfoByProjectName")
    @ApiOperation(value = "通过项目名称模糊查询项目信息")
    public ResultData fuzzySelectProjectInfoByProjectName(@RequestParam("projectName") String projectName,
                                                          @RequestParam("pageNum") Integer pageNum,
                                                          @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> info = projectService.fuzzySelectProjectInfoByProjectName(projectName, pageNum, pageSize);
        return StringUtils.isNotEmpty(String.valueOf(info)) ?
                super.querySuccess(info) : super.queryFailed();
    }

}
