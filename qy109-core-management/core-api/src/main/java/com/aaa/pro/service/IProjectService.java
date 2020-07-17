package com.aaa.pro.service;

import com.aaa.pro.model.*;
import com.aaa.pro.vo.TokenVo;

import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
     * @description: 新增日志
     * @params: loginLogs
     * @return: java.lang.Integer
     * @author: Wen
     * @date: 2020/7/15 16:13
     */
    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLogs loginLogs);

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
    List<Dict> fuzzy2selectDictByTableName(@RequestParam("tableName") String tableName);

    /**
     * @description: 递归查询根据 parentId（父id）查询该部门及其子部门
     * @params: [parentId]
     * @return: java.util.List<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 16:50
     */
    @GetMapping("lectAllDeptByParentId")
    List<Dept> selectAllDeptByParentId(@RequestParam("parentId") Long parentId);


    /**
     * @description: 动态sql 按条件查询部门信信息
     * @params: [map]
     * @return: java.util.List<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 16:50
     */
    @PostMapping("lectDeptInfoByField")
    List<Dept> selectDeptInfoByField(@RequestBody Map map);

    /**
     * @description: 查询部门信息，根据主键id查询部门的信息
     * @params: [deptId]
     * @return: com.aaa.pro.model.Dept
     * @author: Wen
     * @date: 2020/7/16 16:50
     */
    @GetMapping("lectDeptByDeptId")
    Dept selectDeptByDeptId(@RequestParam("deptId") Integer deptId);

    /**
     * @description: 新增部门信息
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 16:51
     */
    @PostMapping("/insertDept")
    Boolean insertDept(@RequestBody Dept dept);

    /**
     * @description: 删除部门信息（根据主键删除）
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 16:51
     */
    @PostMapping("/deleteDeptByPrimaryKey")
    Boolean deleteDeptByPrimaryKey(@RequestBody Dept dept);

    /**
     * @description: 批量删除部门信息（根据主键执行删除操作）
     * @params: [ids]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 16:52
     */
    @PostMapping("/batchDeleteByPrimaryKey")
    Boolean batchDeleteByPrimaryKey(@RequestBody List<Object> ids);

    /**
     * @description: 修改部门信息（根据主键修改）
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 16:53
     */
    @PostMapping("/updateDeptByPrimaryKey")
    Boolean updateDeptByPrimaryKey(@RequestBody Dept dept);


    /**
     * @return java.lang.Boolean
     * @Author: jkm
     * @Description: 添加用户
     * @Date: 18:48 2020/7/16
     * @param: [user]
     */
    @PostMapping("/addUser")
    Boolean addUser(@RequestBody User user);

    /**
     * @Author: jkm
     * @Description: 查询所有用户信息
     */
    @PostMapping("lectAllUser")
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
    @PostMapping("lectUserById")
    User selectUserById(@RequestParam("id") Long id);

    /**
     * @Author: jkm
     * @Description: 根据id修改用户信息
     */
    @PostMapping("/updateUserById")
    Integer updateUserById(@RequestBody User user);

    /**
     * @Author zyb
     * @Description 分页查询所需字段数据(项目信息)
     * @Date 2020/7/17 10:11
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/selectProjectInfoByPage")
    PageInfo<MappingProject> selectProjectInfoByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 通过id查询项目信息一行数据
     * @Date 2020/7/17 10:54
     * @Param [id]
     * @Return com.aaa.pro.model.MappingProject
     **/
    @GetMapping("/selectProjectInfoById")
    MappingProject selectProjectInfoById(@RequestParam("id") Long id);

    /**
     * @Author zyb
     * @Description 通过项目名称模糊查询项目信息
     * @Date 2020/7/17 15:08
     * @Param [projectName]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/fuzzySelectProjectInfoByProjectName")
    PageInfo<MappingProject> fuzzySelectProjectInfoByProjectName(@RequestParam("projectName") String projectName,
                                                                 @RequestParam("pageNum") Integer pageNum,
                                                                 @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->分页查询所需字段数据
     * @Date 2020/7/17 16:12
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/huiJiaoResultsInfoByPage")
    PageInfo<MappingProject> huiJiaoResultsInfoByPage(@RequestParam("pageNum") Integer pageNum,
                                                      @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息)-->根据项目名称分页模糊查询汇交成果信息
     * @Date 2020/7/17 17:13
     * @Param [projectName, pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/fuzzyQueryHuiJiaoByPage")
    PageInfo<MappingProject> fuzzyQueryHuiJiaoByPage(@RequestParam("projectName") String projectName,
                                                     @RequestParam("pageNum") Integer pageNum,
                                                     @RequestParam("pageSize") Integer pageSize);

    /**
     * @description: 测绘管理--单位基本信息
     * @params: [userId]
     * @return: java.util.List<com.aaa.pro.model.Mapping_unit>
     * @author: Wen
     * @date: 2020/7/17 16:29
     */
    @PostMapping("/queryMapping_unit")
    List<Mapping_unit> queryMapping_unit(@RequestParam("userId") Long userId);



    /**
     * @Author zyb
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 19:28
     * @Param [id, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Audit>
     **/
    @GetMapping("/selectAuditRecordByMappingProjectId")
    PageInfo<Audit> selectAuditRecordByMappingProjectId(@RequestParam("id") Long id,
                                                        @RequestParam("pageNum") Integer pageNum,
                                                        @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author: jkm
     * @Description:    查询所有角色信息
     * @Date: 17:35 2020/7/17
     * @param: [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    @GetMapping("/selectAllRole")
    PageInfo selectAllRole(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);

    /**
     *      根据字段属性查询角色信息
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectRoleByField")
    PageInfo selectRoleByField(@RequestBody Map map,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);


    /**
     *      根据主键查询角色信息
     * @param roleId
     * @return
     */
    @GetMapping("selectRoleByPrimaryKey")
    Role selectRoleByPrimaryKey(@RequestParam("roleId") Long roleId);

    /**
     *      根据userId查询设备信息
     * @param userId
     * @return
     */
    @PostMapping("/selectAllEquipmentByUserId")
    List<Equipment> selectAllEquipmentByUserId(@RequestParam("userId") Long userId);

}
