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
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 *   测绘管理--单位审核
 * @author: Wen
 * @date: 2020/7/27 20:25
 */
@RestController
public class MappingUnitAuditController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @description:
     *   查询单位信息（分页）
     * @params: [pageNum, pageSize]
     * @return: com.aaa.pro.base.ResultData
     * @author: Wen
     * @date: 2020/7/27 20:26
     */
    @GetMapping("/selectUnitInfoByPage")
    @ApiOperation(value = "分页查询所需字段数据")
    public ResultData selectUnitInfoByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingUnit> mappingUnitPageInfo = iProjectService.selectUnitInfoByPage(pageNum, pageSize);
        return null != mappingUnitPageInfo && StringUtils.isNotEmpty(String.valueOf(mappingUnitPageInfo)) ?
                super.querySuccess(mappingUnitPageInfo) : super.queryFailed();
    }


    /**
     * @description:
     *   查询某一单位信息（id）
     * @params: [id]
     * @return: com.aaa.pro.base.ResultData
     * @author: Wen
     * @date: 2020/7/27 20:26
     */
    @GetMapping("/selectUnitInfoById")
    @ApiOperation(value = "通过id查询单位详细数据")
    public ResultData selectUnitInfoById(@RequestParam("id") Long id) {
        MappingUnit mappingUnit = iProjectService.selectUnitInfoById(id);
        return null != mappingUnit && StringUtils.isNotEmpty(String.valueOf(mappingUnit)) ?
                super.querySuccess(mappingUnit) : super.queryFailed();
    }


    /**
     * @description:
     *    通过单位名称查询单位信息（模糊查询）
     * @params: [unitName, pageNum, pageSize]
     * @return: com.aaa.pro.base.ResultData
     * @author: Wen
     * @date: 2020/7/27 20:26
     */
    @GetMapping("/fuzzySelectUnitInfoByUnitName")
    @ApiOperation(value = "通过单位名称模糊查询单位信息")
    public ResultData fuzzySelectUnitInfoByUnitName(@RequestParam("unitName") String unitName,
                                                    @RequestParam("pageNum") Integer pageNum,
                                                    @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> mappingProjectPageInfo = iProjectService.fuzzySelectUnitInfoByUnitName(unitName, pageNum, pageSize);
        return StringUtils.isNotEmpty(String.valueOf(mappingProjectPageInfo)) ?
                super.querySuccess(mappingProjectPageInfo) : super.queryFailed();
    }

    /**
     * @description:
     *    修改单位信息
     * @params: [mappingUnit]
     * @return: com.aaa.pro.base.ResultData
     * @author: Wen
     * @date: 2020/7/27 20:57
     */
    @PostMapping("/updateUnitInfo")
    public ResultData updateUnitInfo(@RequestBody MappingUnit mappingUnit){
        return iProjectService.updateUnitInfo(mappingUnit);
    }



}
