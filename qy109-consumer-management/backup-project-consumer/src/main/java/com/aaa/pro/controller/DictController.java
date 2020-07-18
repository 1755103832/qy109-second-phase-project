package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Dict;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/16 10:29
 * @Description
 **/
@RestController
@Api(tags = "字典表接口")
public class DictController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * @Author zyb
     * @Description 查询字典表所有数据
     * @Date 2020/7/16 11:12
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/selectAllDictInfo")
    @ApiOperation(value = "查询字典表所有数据")
    public ResultData selectAllDictInfo() {
        List<Dict> dictList = projectService.selectAllDictInfo();
        return dictList.size() > 0 ? super.querySuccess(dictList) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 新增字典表信息
     * @Date 2020/7/16 11:54
     * @Param [dict]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/addDictInfo")
    @ApiOperation(value = "添加字典表数据")
    public ResultData addDictInfo(@RequestBody Dict dict) {
        Integer integer = projectService.addDictInfo(dict);
        return integer > 0 ? super.insertSuccess() : super.insertFailed();
    }

    /**
     * @Author zyb
     * @Description 通过字典表id批量删除数据
     * @Date 2020/7/16 15:16
     * @Param [ids]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/batchDelByDictIds")
    @ApiOperation(value = "通过id批量删除字典表数据")
    public ResultData batchDelByDictId(@RequestParam("ids") Integer[] ids) {
        Integer dictIds = projectService.batchDelByDictIds(ids);
        return dictIds > 0 ? super.deleteSuccess() : super.deleteFailed();
    }

    /**
     * @Author zyb
     * @Description 通过dictId修改字典表信息
     * @Date 2020/7/16 15:36
     * @Param [dict]
     * @Return java.lang.Integer
     **/
    @PostMapping("/updateByDictId")
    @ApiOperation(value = "通过dictId修改字典表信息")
    public ResultData updateByDictId(@RequestBody Dict dict) {
        Integer dictId = projectService.updateByDictId(dict);
        return dictId > 0 ? super.updateSuccess() : super.updateFailed();
    }

    /**
     * @Author zyb
     * @Description 通过主键dictId查询字典表数据
     * @Date 2020/7/16 16:03
     * @Param [dictId]
     * @Return com.aaa.pro.model.Dict
     **/
    @GetMapping("/selectOneByDictId")
    @ApiOperation(value = "通过主键dictId查询字典表数据")
    public ResultData selectOneByDictId(@RequestParam("dictId") Long dictId) {
        Dict dict = projectService.selectOneByDictId(dictId);
        return null != dict && StringUtils.isNotEmpty(String.valueOf(dict)) ? super.querySuccess() : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 分页查询字典表数据
     * @Date 2020/7/16 17:19
     * @Param [dict, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/selectDictListByPage")
    @ApiOperation(value = "分页查询字典表数据")
    public ResultData selectDictListByPage(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Dict> dictPageInfo = projectService.selectDictListByPage(pageNum, pageSize);
        return null != dictPageInfo && StringUtils.isNotEmpty(String.valueOf(dictPageInfo)) ?
                super.querySuccess(dictPageInfo) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description fuzzy query test
     * @Date 2020/7/16 20:54
     * @Param [tableName]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/fuzzy2selectDictByTableName")
    @ApiOperation(value = "通过表名模糊查询字典表数据")
    public ResultData fuzzy2selectDictByTableName(@RequestParam("tableName") String tableName) {
        List<Dict> dictList = projectService.fuzzy2selectDictByTableName(tableName);
        return null != dictList ? super.querySuccess(dictList) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 分页查询字典表
     * @Date 2020/7/18 16:13
     * @Param [pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByPage")
    @ApiOperation(value = "分页查询字典表")
    public ResultData queryDictByPage(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Dict> info = projectService.queryDictByPage(pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }


    /**
     * @Author zyb
     * @Description 通过key分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByKeyPage")
    @ApiOperation(value = "通过key分页查询字典表")
    public ResultData queryDictByKeyPage(@RequestParam("keyy") Long keyy,
                                         @RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Dict> info = projectService.queryDictByKeyPage(keyy, pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 通过value分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByValuePage")
    @ApiOperation(value = "通过value分页查询字典表")
    public ResultData queryDictByValuePage(@RequestParam("valuee") String valuee,
                                           @RequestParam("pageNum") Integer pageNum,
                                           @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Dict> info = projectService.queryDictByValuePage(valuee, pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 通过字段名分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByFieldNamePage")
    @ApiOperation(value = "通过字段名分页查询字典表")
    public ResultData queryDictByFieldNamePage(@RequestParam("fieldName") String fieldName,
                                               @RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Dict> info = projectService.queryDictByFieldNamePage(fieldName, pageNum, pageSize);
        return StringUtils.isNotEmpty(info.toString()) ? super.querySuccess(info) : super.queryFailed();
    }
}
