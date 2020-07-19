package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.MappingUnit;
import com.aaa.pro.service.MappingUnitService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/18 18:56
 * @Description
 **/
@RestController
public class MappingUnitController extends CommonController<MappingUnit> {

    @Autowired
    private MappingUnitService mappingUnitService;

    /**
     * getBaseService
     *
     * @return
     */
    @Override
    public BaseService<MappingUnit> getBaseService() {
        return mappingUnitService;
    }

    /**
     * @Author zyb
     * @Description 查询白名单人员信息
     * @Date 2020/7/18 18:55
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    @GetMapping("/selectByUnitStatus")
    public PageInfo<MappingUnit> selectByUnitStatus(@RequestParam("pageNum") Integer pageNum,
                                                    @RequestParam("pageSize") Integer pageSize) {
        return mappingUnitService.selectByUnitStatus(pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 查询黑名单人员信息
     * @Date 2020/7/18 19:05
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    @GetMapping("/selectByUnitStatus2")
    public PageInfo<MappingUnit> selectByUnitStatus2(@RequestParam("pageNum") Integer pageNum,
                                                     @RequestParam("pageSize") Integer pageSize) {
        return mappingUnitService.selectByUnitStatus2(pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 根据抽查比例查询单位信息
     * @Date 2020/7/19 9:07
     * @Param [random, ownedDistrict, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    @GetMapping("/selectByRatioAndType")
    public PageInfo<MappingUnit> selectByRatioAndType(@RequestParam(value = "random", required = false)
                                                              Double random,
                                                      @RequestParam(value = "ownedDistrict", required = false)
                                                              String ownedDistrict,
                                                      @RequestParam("pageNum") Integer pageNum,
                                                      @RequestParam("pageSize") Integer pageSize) {
        return mappingUnitService.selectByRatioAndType(random, ownedDistrict, pageNum, pageSize);
    }
}
