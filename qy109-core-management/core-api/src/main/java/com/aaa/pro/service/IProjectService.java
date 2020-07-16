package com.aaa.pro.service;


import com.aaa.pro.model.LoginLogs;
import com.aaa.pro.model.User;
import com.aaa.pro.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 17:31
 * @Description
 **/
@FeignClient(value = "")
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
   * @description:
   *   新增日志
   * @params:   loginLogs
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
     * @Author: jkm
     * @Description:
     *      添加用户
     * @Date: 18:48 2020/7/16
     * @param: [user]
     * @return java.lang.Boolean
     */
    @PostMapping("/addUser")
    Boolean addUser(@RequestBody User user);

    /**
      * @Author: jkm
      * @Description: 查询所有用户信息
      */
    @PostMapping("/selectAllUser")
    PageInfo selectAllUser(@RequestParam("pageNo") Integer pageNo,
                           @RequestParam("pageSize") Integer pageSize);

    /**
      * @Author: jkm
      * @Description: 根据主键删除用户
      */
    @PostMapping("/deleteUser")
    Integer deleteUser(@RequestBody User user);

    /**
      * @Author: jkm
      * @Description: 根据id 批量删除用户
      */
    @PostMapping("/deleteMoreUser")
    Integer deletrMoreUser(@RequestBody List<Object> ids);

    /**
      * @Author: jkm
      * @Description: 根据id查询用户
      */
    @PostMapping("/selectUserById")
    User selectUserById(@RequestParam("id") Long id);

    /**
      * @Author: jkm
      * @Description: 根据id修改用户信息
      */
    @PostMapping("/updateUserById")
    Integer updateUserById(@RequestBody User user);
}
