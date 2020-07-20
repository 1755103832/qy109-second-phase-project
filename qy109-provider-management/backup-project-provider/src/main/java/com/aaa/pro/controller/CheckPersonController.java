package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.CheckPerson;
import com.aaa.pro.service.CheckPersonService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/20 17:09
 * @Description
 **/
@RestController
public class CheckPersonController extends CommonController<CheckPerson> {

    @Autowired
    private CheckPersonService checkPersonService;

    /**
     * getBaseService
     *
     * @return
     */
    @Override
    public BaseService<CheckPerson> getBaseService() {
        return checkPersonService;
    }

    /**
     * @Author zyb
     * @Description 根据抽查比例分页查询抽查人员信息
     * @Date 2020/7/20 17:04
     * @Param [random, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.CheckPerson>
     **/
    @GetMapping("/selectByRatioPage")
    public PageInfo<CheckPerson> selectByRatioPage(@RequestParam(value = "random", required = false)
                                                           Double random,
                                                   @RequestParam("pageNum") Integer pageNum,
                                                   @RequestParam("pageSize") Integer pageSize) {
        return checkPersonService.selectByRatioPage(random, pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 新增抽查人员信息
     * @Date 2020/7/20 18:12
     * @Param [checkPerson]
     * @Return Boolean
     **/
    @PostMapping("/addCheckPersonInfo")
    public Integer addCheckPersonInfo(@RequestBody CheckPerson checkPerson) {
        return checkPersonService.addCheckPersonInfo(checkPerson);
    }

    /**
     * @Author zyb
     * @Description 修改抽查人员信息
     * @Date 2020/7/20 20:21
     * @Param [checkPerson]
     * @Return java.lang.Integer
     **/
    @PostMapping("/updateCheckPersonInfo")
    public Integer updateCheckPersonInfo(@RequestBody CheckPerson checkPerson) {
        return checkPersonService.updateCheckPersonInfo(checkPerson);
    }

    /**
     * @Author zyb
     * @Description 通过主键id删除抽查人员信息
     * @Date 2020/7/20 20:40
     * @Param [id]
     * @Return java.lang.Integer
     **/
    @PostMapping("/delCheckPersonInfoById")
    public Integer delCheckPersonInfoById(@RequestParam("id") Long id) {
        return checkPersonService.delCheckPersonInfoById(id);
    }
}
