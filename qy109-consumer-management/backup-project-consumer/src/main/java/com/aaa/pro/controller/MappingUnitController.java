package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.MappingUnit;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/18 18:58
 * @Description
 **/
@RestController
@Api(tags = "黑白名单接口")
public class MappingUnitController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * @Author zyb
     * @Description 查询白名单人员信息
     * @Date 2020/7/18 19:02
     * @Param [pageNum, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/selectByUnitStatus")
    @ApiOperation(value = "查询白名单人员信息")
    public ResultData selectByUnitStatus(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingUnit> info = projectService.selectByUnitStatus(pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 查询黑名单人员信息
     * @Date 2020/7/18 19:07
     * @Param [pageNum, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/selectByUnitStatus2")
    @ApiOperation(value = "查询黑名单人员信息")
    public ResultData selectByUnitStatus2(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingUnit> info = projectService.selectByUnitStatus2(pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 根据抽查比例和单位类型查询单位信息
     * @Date 2020/7/19 9:18
     * @Param [random, ownedDistrict, pageNum, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/selectByRatioAndType")
    @ApiOperation(value = "根据抽查比例和单位类型查询单位信息")
    public ResultData selectByRatioAndType(@RequestParam(value = "random", required = false)
                                                   Double random,
                                           @RequestParam(value = "ownedDistrict", required = false)
                                                   String ownedDistrict,
                                           @RequestParam("pageNum") Integer pageNum,
                                           @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingUnit> info = projectService.selectByRatioAndType(random, ownedDistrict, pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }
}
