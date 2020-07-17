package com.aaa.pro.service;

import com.aaa.pro.model.Dict;
import com.aaa.pro.model.LoginLogs;
import com.aaa.pro.model.User;
import com.aaa.pro.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 17:31
 * @Description
 **/
@FeignClient(value = "backup-project-interface")
public interface IProjectService {

    /**
     * @Author zyb
     * @Description 执行登录操作
     * @Date 2020/7/13 17:33
     * @Param [user]
     * @Return com.aaa.pro.vo.TokenVo
     **/
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);

    /**
     * @description: 新增日志
     * @params: loginLogs
     * @return: java.lang.Integer
     * @author: Wen
     * @date: 2020/7/15 16:13
     */
    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLogs loginLogs);

    // TODO: ftp上传已写完
    //  (uploadController类,uploadFile方法,
    //  BaseController中uploadSuccess,uploadFalse方法)，其余操作待补充

    /**
     * @return java.lang.Boolean
     * @Author: jkm
     * @Description: ftp文件上传
     * 因为feign默认只能发送普通类型(java8种基本类型，封装类型，集合...)
     * 这些普通类型都可以转换为二进制流的形式在http之间传输，但是文件类型不行，
     * 因为文件类型只能转换为字节流/字符流
     * 也就是说，最终我可以让PostMapping去接收Multipart/form-data类型
     * 让feign使用json的数据格式来进行接收
     * @Date: 20:49 2020/7/14
     * @param: [file]
     */
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file);

    /**
     * @Author zyb
     * @Description selectAllDictInfo
     * @Date 2020/7/16 11:07
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/selectAllDictInfo")
    List<Dict> selectAllDictInfo();

    /**
     * @Author zyb
     * @Description 新增字典表信息
     * @Date 2020/7/16 11:53
     * @Param [dict]
     * @Return java.lang.Integer
     **/
    @PostMapping("/addDictInfo")
    Integer addDictInfo(@RequestBody Dict dict);

    /**
     * @Author zyb
     * @Description 通过字典表id批量删除数据
     * @Date 2020/7/16 15:13
     * @Param [ids]
     * @Return java.lang.Integer
     **/
    @PostMapping("/batchDelByDictIds")
    Integer batchDelByDictIds(@RequestParam("ids") Integer[] ids);

    /**
     * @Author zyb
     * @Description 通过dictId修改字典表信息
     * @Date 2020/7/16 15:36
     * @Param [dict]
     * @Return java.lang.Integer
     **/
    @PostMapping("/updateByDictId")
    Integer updateByDictId(@RequestBody Dict dict);

    /**
     * @Author zyb
     * @Description 通过主键dictId查询字典表数据
     * @Date 2020/7/16 16:03
     * @Param [dictId]
     * @Return com.aaa.pro.model.Dict
     **/
    @GetMapping("/selectOneByDictId")
    Dict selectOneByDictId(@RequestParam("dictId") Long dictId);

    /**
     * @Author zyb
     * @Description 分页查询字典表数据
     * @Date 2020/7/16 17:19
     * @Param [dict, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/selectDictListByPage")
    PageInfo<Dict> selectDictListByPage(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description fuzzy query test
     * @Date 2020/7/16 20:54
     * @Param [tableName]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/fuzzy2selectDictByTableName")
    List<Dict> fuzzy2selectDictByTableName(String tableName);
}
