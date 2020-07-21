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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 10:23
 * @Description
 **/
@RestController
@Api(tags = "项目信息接口")
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

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->分页查询所需字段数据
     * @Date 2020/7/17 16:12
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/huiJiaoResultsInfoByPage")
    @ApiOperation(value = "项目审核(汇交成果信息)-->分页查询所需字段数据")
    public ResultData huiJiaoResultsInfoByPage(@RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> info = projectService.huiJiaoResultsInfoByPage(pageNum, pageSize);
        return StringUtils.isNotEmpty(String.valueOf(info)) ?
                super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->根据项目名称分页模糊查询汇交成果信息
     * @Date 2020/7/17 17:13
     * @Param [projectName, pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/fuzzyQueryHuiJiaoByPage")
    @ApiOperation(value = "项目审核(汇交成果信息)-->根据项目名称分页模糊查询汇交成果信息")
    public ResultData fuzzyQueryHuiJiaoByPage(@RequestParam("projectName") String projectName,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> info = projectService.fuzzyQueryHuiJiaoByPage(projectName, pageNum, pageSize);
        return StringUtils.isNotEmpty(String.valueOf(info)) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 项目审核-->汇交成果信息-->操作-->查看按钮(项目信息+附件)
     * @Date 2020/7/18 11:33
     * @Param [id]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("/queryProjectInfoById")
    @ApiOperation(value = "项目审核-->汇交成果信息-->操作-->查看按钮(项目信息+附件)")
    public ResultData queryProjectInfoById(@RequestParam("id") Long id) {
        List<Map<String, Object>> map = projectService.queryProjectInfoById(id);
        return map.size() > 0 ? super.querySuccess(map) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 项目审核-->汇交成果信息-->操作-->查看按钮(汇交结果+附件)
     * @Date 2020/7/18 11:50
     * @Param [id]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("/queryHuiJiaoResultsById")
    @ApiOperation(value = "项目审核-->汇交成果信息-->操作-->查看按钮(汇交结果+附件)")
    public ResultData queryHuiJiaoResultsById(@RequestParam("id") Long id) {
        List<Map<String, Object>> map = projectService.queryHuiJiaoResultsById(id);
        return map.size() > 0 ? super.querySuccess(map) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 项目审核模块(项目审核界面)
     * @Date 2020/7/18 15:32
     * @Param [pageNum, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/queryStatus2")
    @ApiOperation(value = "项目审核模块(项目审核界面)")
    public ResultData queryStatus2(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> info = projectService.queryStatus2(pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 项目审核模块(项目审核界面 - - > 根据项目名称模糊查询)
     * @Date 2020/7/18 15:43
     * @Param [projectName, pageNum, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/fuzzyQueryStatus2")
    @ApiOperation(value = "项目审核模块(项目审核界面 - - > 根据项目名称模糊查询)")
    public ResultData fuzzyQueryStatus2(@RequestParam("projectName") String projectName,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> info = projectService.fuzzyQueryStatus2(projectName, pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 项目审核模块(成果汇交审核界面 - - > 分页查询)
     * @Date 2020/7/18 16:02
     * @Param [pageNum, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/resultsHuiJiaoAuditByPage")
    @ApiOperation(value = "项目审核模块(成果汇交审核界面 - - > 分页查询)")
    public ResultData resultsHuiJiaoAuditByPage(@RequestParam("pageNum") Integer pageNum,
                                                @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> info = projectService.resultsHuiJiaoAuditByPage(pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 通过项目名称模糊查询+查询所有并分页
     * @Date 2020/7/21 16:09
     * @Param [mappingProject, pageNo, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/selectFuzzy")
    @ApiOperation(value = "通过项目名称模糊查询+查询所有并分页")
    public ResultData selectFuzzy(@RequestBody(required = false) MappingProject mappingProject,
                                  @RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> info = projectService.selectFuzzy(mappingProject, pageNum, pageSize);
        return info.getSize() > 0 ? super.querySuccess(info) : super.queryFailed();
    }

}
