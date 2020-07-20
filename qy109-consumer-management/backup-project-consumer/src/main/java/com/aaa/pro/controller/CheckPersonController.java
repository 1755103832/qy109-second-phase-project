package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.CheckPerson;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/20 17:15
 * @Description
 **/
@RestController
@Api(tags = "双随机抽查接口")
public class CheckPersonController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * @Author zyb
     * @Description 根据抽查比例分页查询抽查人员信息
     * @Date 2020/7/20 17:19
     * @Param [random, pageNum, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/selectByRatioPage")
    @ApiOperation(value = "根据抽查比例分页查询抽查人员信息")
    public ResultData selectByRatioPage(@RequestParam(value = "random", required = false)
                                                Double random,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize) {
        PageInfo<CheckPerson> info = projectService.selectByRatioPage(random, pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 新增抽查人员信息
     * @Date 2020/7/20 18:21
     * @Param [checkPerson]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/addCheckPersonInfo")
    @ApiOperation(value = "新增抽查人员信息")
    public ResultData addCheckPersonInfo(@RequestBody CheckPerson checkPerson) {
        Integer personInfo = projectService.addCheckPersonInfo(checkPerson);
        return personInfo > 0 ? super.insertSuccess(checkPerson) : super.insertFailed();
    }

    /**
     * @Author zyb
     * @Description 修改抽查人员信息
     * @Date 2020/7/20 20:28
     * @Param [checkPerson]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/updateCheckPersonInfo")
    @ApiOperation(value = "修改抽查人员信息")
    public ResultData updateCheckPersonInfo(@RequestBody CheckPerson checkPerson) {
        Integer integer = projectService.updateCheckPersonInfo(checkPerson);
        return integer > 0 ? super.updateSuccess(checkPerson) : super.updateFailed();
    }

    /**
     * @Author zyb
     * @Description 通过主键id删除抽查人员信息
     * @Date 2020/7/20 20:40
     * @Param [id]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/delCheckPersonInfoById")
    @ApiOperation(value = "通过主键id删除抽查人员信息")
    public ResultData delCheckPersonInfoById(@RequestParam("id") Long id) {
        Integer integer = projectService.delCheckPersonInfoById(id);
        return integer > 0 ? super.deleteSuccess(id) : super.deleteFailed();
    }
}
