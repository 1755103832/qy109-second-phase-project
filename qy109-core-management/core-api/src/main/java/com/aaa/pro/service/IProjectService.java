package com.aaa.pro.service;

import com.aaa.pro.model.Dept;
import com.aaa.pro.model.LoginLogs;
import com.aaa.pro.model.User;
import com.aaa.pro.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    * @description: 
    *  递归查询根据 parentId（父id）查询该部门及其子部门
    * @params: [parentId]
    * @return: java.util.List<com.aaa.pro.model.Dept>
    * @author: Wen
    * @date: 2020/7/16 16:50
    */   
    @GetMapping("/selectAllDeptByParentId")
    List<Dept> selectAllDeptByParentId(@RequestParam("parentId") Long parentId);


    /**
     * @description: 
     *     动态sql 按条件查询部门信信息
     * @params: [map]
     * @return: java.util.List<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 16:50
     */   
    @PostMapping("/selectDeptInfoByField")
    List<Dept> selectDeptInfoByField(@RequestBody Map map);

    /**
     * @description:
     *  查询部门信息，根据主键id查询部门的信息
     * @params: [deptId]
     * @return: com.aaa.pro.model.Dept
     * @author: Wen
     * @date: 2020/7/16 16:50
     */   
    @GetMapping("/selectDeptByDeptId")
    Dept selectDeptByDeptId(@RequestParam("deptId") Integer deptId);

    /**
     * @description:
     *  新增部门信息
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 16:51
     */
    @PostMapping("/insertDept")
    Boolean insertDept(@RequestBody Dept dept);

    /**
     * @description:
     *   删除部门信息（根据主键删除）
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 16:51
     */
    @PostMapping("/deleteDeptByPrimaryKey")
    Boolean deleteDeptByPrimaryKey(@RequestBody Dept dept);

    /**
     * @description:
     *   批量删除部门信息（根据主键执行删除操作）
     * @params: [ids]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 16:52
     */
    @PostMapping("/batchDeleteByPrimaryKey")
    Boolean batchDeleteByPrimaryKey(@RequestBody List<Object> ids);

    /**
     * @description:
     *   修改部门信息（根据主键修改）
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 16:53
     */
    @PostMapping("/updateDeptByPrimaryKey")
    Boolean updateDeptByPrimaryKey(@RequestBody Dept dept);


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