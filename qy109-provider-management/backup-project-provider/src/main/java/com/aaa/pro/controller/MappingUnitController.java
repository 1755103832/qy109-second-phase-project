package com.aaa.pro.controller;

import cn.hutool.core.date.DateUtil;
import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.MappingUnit;
import com.aaa.pro.service.MappingUnitService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 *    测绘管理--单位信息
 * @author: Wen
 * @date: 2020/7/23 20:12
 */
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
     * @description:
     *   分页查询单位信息
     * @params: [pageNum, pageSize]
     * @return: com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     * @author: Wen
     * @date: 2020/7/22 10:25
     */
    @GetMapping("/selectUnitInfoByPage")
    public PageInfo<MappingUnit> selectUnitInfoByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return mappingUnitService.selectUnitInfoByPage(pageNum, pageSize);
    }

    /**
     * @description:
     *   查询单位信息（id）
     * @params: [id]
     * @return: com.aaa.pro.model.MappingUnit
     * @author: Wen
     * @date: 2020/7/22 11:34
     */
    @GetMapping("/selectUnitInfoById")
    public MappingUnit selectUnitInfoById(@RequestParam("id") Long id) {
        return mappingUnitService.selectUnitInfoById(id);
    }

    /**
     * @description:
     *    根据单位名称查询单位信息（模糊查询）
     * @params: [unitName, pageNum, pageSize]
     * @return: com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     * @author: Wen
     * @date: 2020/7/22 11:37
     */
    @GetMapping("/fuzzySelectUnitInfoByUnitName")
    public PageInfo<MappingUnit> fuzzySelectUnitInfoByUnitName(@RequestParam("unitName") String unitName,
                                                               @RequestParam("pageNum") Integer pageNum,
                                                               @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingUnit> mappingUnits = mappingUnitService.fuzzySelectUnitInfoByUnitName(unitName);
        return mappingUnits.size() > 0 ? new PageInfo<>(mappingUnits) : null;

    }

    /**
     * @description:
     *   修改单位信息
     * @params: [mappingUnit]
     * @return: ResultData
     * @author: Wen
     * @date: 2020/7/27 20:37
     */
    @PostMapping("/updateUnitInfo")
    ResultData updateUnitInfo(@RequestBody MappingUnit mappingUnit){
        mappingUnit.setModifyTime(DateUtil.now());
        int i = mappingUnitService.updateUnitInfo(mappingUnit);
        if(i > 0){
            return super.updateSuccess();
        }else{
            return super.updateFailed();
        }
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
