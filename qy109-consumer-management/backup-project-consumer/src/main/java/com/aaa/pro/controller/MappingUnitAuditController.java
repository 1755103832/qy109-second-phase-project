package com.aaa.pro.controller;


import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.MappingProject;
import com.aaa.pro.model.MappingUnit;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingUnitAuditController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    @GetMapping("/selectUnitInfoByPage")
    @ApiOperation(value = "分页查询所需字段数据")
    public ResultData selectUnitInfoByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingUnit> mappingUnitPageInfo = iProjectService.selectUnitInfoByPage(pageNum, pageSize);
        return null != mappingUnitPageInfo && StringUtils.isNotEmpty(String.valueOf(mappingUnitPageInfo)) ?
                super.querySuccess(mappingUnitPageInfo) : super.queryFailed();
    }

    @GetMapping("/selectUnitInfoById")
    @ApiOperation(value = "通过id查询单位详细数据")
    public ResultData selectUnitInfoById(@RequestParam("id") Long id) {
        MappingUnit mappingUnit = iProjectService.selectUnitInfoById(id);
        return null != mappingUnit && StringUtils.isNotEmpty(String.valueOf(mappingUnit)) ?
                super.querySuccess(mappingUnit) : super.queryFailed();
    }


    @GetMapping("/fuzzySelectUnitInfoByUnitName")
    @ApiOperation(value = "通过单位名称模糊查询单位信息")
    public ResultData fuzzySelectUnitInfoByUnitName(@RequestParam("unitName") String unitName,
                                                    @RequestParam("pageNum") Integer pageNum,
                                                    @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> mappingProjectPageInfo = iProjectService.fuzzySelectUnitInfoByUnitName(unitName, pageNum, pageSize);
        return StringUtils.isNotEmpty(String.valueOf(mappingProjectPageInfo)) ?
                super.querySuccess(mappingProjectPageInfo) : super.queryFailed();
    }

}
