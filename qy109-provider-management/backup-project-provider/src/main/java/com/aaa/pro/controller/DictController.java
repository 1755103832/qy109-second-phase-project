package com.aaa.pro.controller;

import com.aaa.pro.model.Dict;
import com.aaa.pro.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/16 10:18
 * @Description
 **/
@RestController
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * @Author zyb
     * @Description select all dict info
     * @Date 2020/7/16 10:24
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/selectAllDictInfo")
    public List<Dict> selectAllDictInfo() {
        return dictService.selectAllDictInfo();
    }

    /**
     * @Author zyb
     * @Description 新增字典表信息
     * @Date 2020/7/16 11:52
     * @Param [dict]
     * @Return java.lang.Integer
     **/
    @PostMapping("/addDictInfo")
    public Integer addDictInfo(@RequestBody Dict dict) {
        return dictService.addDictInfo(dict);
    }

    /**
     * @Author zyb
     * @Description 通过字典表id批量删除数据
     * @Date 2020/7/16 15:12
     * @Param [ids]
     * @Return java.lang.Integer
     **/
    @PostMapping("/batchDelByDictIds")
    public Integer batchDelByDictIds(@RequestParam("ids") Integer[] ids) {
        return dictService.batchDeleteByDictIds(Arrays.asList(ids));
    }

    /**
     * @Author zyb
     * @Description 通过dictId修改字典表信息
     * @Date 2020/7/16 15:36
     * @Param [dict]
     * @Return java.lang.Integer
     **/
    @PostMapping("/updateByDictId")
    public Integer updateByDictId(@RequestBody Dict dict) {
        return dictService.updateByDictId(dict);
    }

    /**
     * @Author zyb
     * @Description 通过主键dictId查询字典表数据
     * @Date 2020/7/16 16:03
     * @Param [dictId]
     * @Return com.aaa.pro.model.Dict
     **/
    @GetMapping("/selectOneByDictId")
    public Dict selectOneByDictId(@RequestParam("dictId") Long dictId) {
        return dictService.selectOneByDictId(dictId);
    }

    /**
     * @Author zyb
     * @Description 分页查询字典表数据
     * @Date 2020/7/16 17:19
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/selectDictListByPage")
    public PageInfo<Dict> selectDictListByPage(@RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        return dictService.selectDictListByPage(pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description fuzzy query test
     * @Date 2020/7/16 20:54
     * @Param [tableName]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/fuzzy2selectDictByTableName")
    public List<Dict> fuzzy2selectDictByTableName(@RequestParam("tableName") String tableName) {
        return dictService.fuzzy2selectDictByTableName(tableName);
    }

    /**
     * @Author zyb
     * @Description 分页查询字典表
     * @Date 2020/7/18 16:13
     * @Param [pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByPage")
    public PageInfo<Dict> queryDictByPage(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize) {
        return dictService.queryDictByPage(pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 通过key分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByKeyPage")
    public PageInfo<Dict> queryDictByKeyPage(@RequestParam("keyy") Long keyy,
                                             @RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize) {
        return dictService.queryDictByKeyPage(keyy, pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 通过value分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByValuePage")
    public PageInfo<Dict> queryDictByValuePage(@RequestParam("valuee") String valuee,
                                               @RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        return dictService.queryDictByValuePage(valuee, pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 通过字段名分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByFieldNamePage")
    public PageInfo<Dict> queryDictByFieldNamePage(@RequestParam("fieldName") String fieldName,
                                                   @RequestParam("pageNum") Integer pageNum,
                                                   @RequestParam("pageSize") Integer pageSize) {
        return dictService.queryDictByFieldNamePage(fieldName, pageNum, pageSize);
    }
}
