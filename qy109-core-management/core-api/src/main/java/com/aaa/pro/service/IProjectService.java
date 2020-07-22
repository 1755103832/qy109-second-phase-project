package com.aaa.pro.service;

import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.*;
import com.aaa.pro.vo.RoleVo;
import com.aaa.pro.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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
    @GetMapping("/selectAllDeptByParentId")
    List<Dept> selectAllDeptByParentId(@RequestParam("parentId") Long parentId);


    /**
     * @description: 动态sql 按条件查询部门信信息
     * @params: [map]
     * @return: java.util.List<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 16:50
     */
    @PostMapping("/selectDeptInfoByField")
    List<Dept> selectDeptInfoByField(@RequestBody Map map);

    /**
     * @description: 查询部门信息，根据主键id查询部门的信息
     * @params: [deptId]
     * @return: com.aaa.pro.model.Dept
     * @author: Wen
     * @date: 2020/7/16 16:50
     */
    @GetMapping("/selectDeptByDeptId")
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
     * @Description: 添加用户接口
     * @Author: jkm
     * @Date: 2020/5/20 14:42
     * @Param: [user]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/user/addUser")
    ResultData addUser(@RequestBody User user);


    /**
     * @Description: 批量删除用户
     * @Author: jkm
     * @Date: 2020/5/20 20:33
     * @Param: [ids]
     * @return: com.aaa.pro.base.ResultData
     */
    @DeleteMapping("/user/delUser")
    ResultData delUser(@RequestBody List<Long> ids);

    /**
     * @Description: 用户管理中修改用户信息
     * @Author: jkm
     * @Date: 2020/5/21 15:48
     * @Param: [user]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/user/updateUser")
    ResultData updateUser(@RequestBody User user);

    /**
     * @Description: 用户信息导出excle
     * @Author: jkm
     * @Date: 2020/5/21 16:25
     * @Param: []
     */
    @GetMapping("/user/exportExcle")
    Response exportExcle();


    /**
     * @return com.aaa.pro.base.ResultData
     * @throws
     * @Author Cy
     * @Description 条件分页查询所有用户
     * @Param [map]
     * @Data 2020/5/21
     */
    @PostMapping("/user/selectUser")
    ResultData selectUserAll(@RequestBody HashMap map);

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
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 11:45
     * @Param [id]
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    @GetMapping("/selectProjectAuditInfoByMappingProjectTableId")
    PageInfo selectProjectAuditInfoByMappingProjectTableId(@RequestParam("id") Long id,
                                                           @RequestParam("pageNum") Integer pageNum,
                                                           @RequestParam("pageSize") Integer pageSize);

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
     * @description: 测绘管理--单位基本信息--查询单位负责人信息
     * @params: [userId]
     * @return: java.util.List<com.aaa.pro.model.Principal>
     * @author: Wen
     * @date: 2020/7/17 22:35
     */
    @PostMapping("/queryPrincipal")
    List<Principal> queryOne(@RequestParam("userId") Long userId);

    /**
     * @description: 测绘管理-单位基本信息--修改负责人信息
     * @params: [principal]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/17 22:36
     */
    @PostMapping("/updateList")
    Boolean updateList(@RequestBody Principal principal);

    /**
     * @description: 单位审核--查询技术人员信息
     * @params: [userId]
     * @return: java.util.List<com.aaa.pro.model.Technicist>
     * @author: Wen
     * @date: 2020/7/18 16:08
     */
    @PostMapping("/queryTechnicist")
    List<Technicist> queryTechnicist(@RequestParam("userId") Long userId);

    /**
     * @description: 单位审核--修改技术人员信息
     * @params: [technicist]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/18 16:09
     */
    @PostMapping("/updateTechnicist")
    Boolean updateTechnicist(@RequestBody Technicist technicist);

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
     * @return java.util.List<com.aaa.pro.model.Equipment>
     * @Author: jkm
     * @Description: 根据 userId 查询设备信息
     * @Date: 19:30 2020/7/17
     * @param: [userId]
     */
    @GetMapping("/selectAllEquipmentByUserId")
    List<Equipment> selectAllEquipmentByUserId(@RequestParam("userId") Long userId);

    /**
     * @Description: 查询所有的角色
     * @Author: jkm
     * @Date: 2020/6/3 18:58
     * @Param: []
     * @return: com.aaa.pro.base.ResultData
     */
    @GetMapping("/role/allRoles")
    ResultData selectAllRole();

    /**
     * @Description: 简单的分页查询
     * @Author: jkm
     * @Date: 2020/6/3 18:54
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/role/pageRoles")
    ResultData selectAllRoleByPage(@RequestBody RoleVo roleVo);


    /**
     * @Description: 删除角色
     * @Author: jkm
     * @Date: 2020/6/3 18:54
     * @Param: [roleId]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/role/deleteRole")
    ResultData deleteRole(@RequestParam("roleId") Long roleId);


    /**
     * @Description: 新增角色以及批量新增权限
     * @Author: jkm
     * @Date: 2020/6/3 18:54
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/role/insertRole")
    ResultData insertRole(@RequestBody RoleVo roleVo);


    /**
     * @Description: 修改角色及其权限
     * @Author: jkm
     * @Date: 2020/6/3 18:54
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/role/updateRole")
    ResultData updateRole(@RequestBody RoleVo roleVo);

    /**
     * @Author zyb
     * @Description 根据关联业务编号查询审核记录
     * @Date 2020/7/18 9:13
     * @Param [refId, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Audit>
     **/
    @GetMapping("/selectAuditByRefId")
    PageInfo<Audit> selectAuditByRefId(@RequestParam("refId") Long refId,
                                       @RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息 - - > 查看审核记录 ( 按钮))
     * @Date 2020/7/18 9:31
     * @Param [id, pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.Audit>
     **/
    @GetMapping("/selectAuditByMappingProjectId")
    PageInfo<Audit> selectAuditByMappingProjectId(@RequestParam("id") Long id,
                                                  @RequestParam("pageNum") Integer pageNum,
                                                  @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 项目审核-->汇交成果信息-->操作-->查看按钮(项目信息+附件)
     * @Date 2020/7/18 11:33
     * @Param [id]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("/queryProjectInfoById")
    List<Map<String, Object>> queryProjectInfoById(@RequestParam("id") Long id);

    /**
     * @Author zyb
     * @Description 项目审核-->汇交成果信息-->操作-->查看按钮(汇交结果+附件)
     * @Date 2020/7/18 11:46
     * @Param [id]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     **/
    @GetMapping("/queryHuiJiaoResultsById")
    List<Map<String, Object>> queryHuiJiaoResultsById(@RequestParam("id") Long id);

    /**
     * @Author zyb
     * @Description 项目审核模块(项目审核界面)
     * @Date 2020/7/18 15:29
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/queryStatus2")
    PageInfo<MappingProject> queryStatus2(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 项目审核模块(项目审核界面 - - > 根据项目名称模糊查询)
     * @Date 2020/7/18 15:41
     * @Param [projectName, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/fuzzyQueryStatus2")
    PageInfo<MappingProject> fuzzyQueryStatus2(@RequestParam("projectName") String projectName,
                                               @RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 项目审核模块(成果汇交审核界面 - - > 分页查询)
     * @Date 2020/7/18 15:59
     * @Param []
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingProject>
     **/
    @GetMapping("/resultsHuiJiaoAuditByPage")
    PageInfo<MappingProject> resultsHuiJiaoAuditByPage(@RequestParam("pageNum") Integer pageNum,
                                                       @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 分页查询字典表
     * @Date 2020/7/18 16:13
     * @Param [pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByPage")
    PageInfo<Dict> queryDictByPage(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 通过key分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByKeyPage")
    PageInfo<Dict> queryDictByKeyPage(@RequestParam("keyy") Long keyy,
                                      @RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 通过value分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByValuePage")
    PageInfo<Dict> queryDictByValuePage(@RequestParam("valuee") String valuee,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 通过字段名分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    @GetMapping("/queryDictByFieldNamePage")
    PageInfo<Dict> queryDictByFieldNamePage(@RequestParam("fieldName") String fieldName,
                                            @RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 查询白名单人员信息
     * @Date 2020/7/18 18:55
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    @GetMapping("/selectByUnitStatus")
    PageInfo<MappingUnit> selectByUnitStatus(@RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 查询黑名单人员信息
     * @Date 2020/7/18 19:05
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    @GetMapping("/selectByUnitStatus2")
    PageInfo<MappingUnit> selectByUnitStatus2(@RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 根据抽查比例查询单位信息
     * @Date 2020/7/19 9:07
     * @Param [random, ownedDistrict, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    @GetMapping("/selectByRatioAndType")
    PageInfo<MappingUnit> selectByRatioAndType(@RequestParam(value = "random", required = false)
                                                       Double random,
                                               @RequestParam(value = "ownedDistrict", required = false)
                                                       String ownedDistrict,
                                               @RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 根据抽查比例分页查询抽查人员信息
     * @Date 2020/7/20 17:04
     * @Param [random, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.CheckPerson>
     **/
    @GetMapping("/selectByRatioPage")
    PageInfo<CheckPerson> selectByRatioPage(@RequestParam(value = "random", required = false)
                                                    Double random,
                                            @RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author zyb
     * @Description 新增抽查人员信息
     * @Date 2020/7/20 18:12
     * @Param [checkPerson]
     * @Return Boolean
     **/
    @PostMapping("/addCheckPersonInfo")
    Integer addCheckPersonInfo(@RequestBody CheckPerson checkPerson);

    /**
     * @Author zyb
     * @Description 修改抽查人员信息
     * @Date 2020/7/20 20:21
     * @Param [checkPerson]
     * @Return java.lang.Integer
     **/
    @PostMapping("/updateCheckPersonInfo")
    Integer updateCheckPersonInfo(@RequestBody CheckPerson checkPerson);

    /**
     * @Author zyb
     * @Description 通过主键id删除抽查人员信息
     * @Date 2020/7/20 20:40
     * @Param [id]
     * @Return java.lang.Integer
     **/
    @PostMapping("/delCheckPersonInfoById")
    Integer delCheckPersonInfoById(@RequestParam("id") Long id);

    /**
     * @Description 公告栏信息
     * @Author jkm
     * @Date 2020/7/20 20:33
     */
    @PostMapping("/newsPostPageInfo")
    PageInfo<News> newsPostPageInfo(News news, Integer pageNo, Integer pageSize);

    /**
     * @Description 根据id 删除公告
     * @Author jkm
     * @Date 2020/7/21 17:31
     */
    @PostMapping("/newsDelete")
    Integer newsDelete(@RequestBody News news);

    /**
     * @Description 根据id 批量删除公告
     * @Author jkm
     * @Date 2020/7/21 17:31
     */
    @PostMapping("/newsDeleteByIds")
    Integer newsDeleteByIds(@RequestParam("ids[]") List<Integer> ids);


    /**
     * @Description 修改公告
     * @Author jkm
     * @Date 2020/7/21 17:33
     */
    @PostMapping("/newsUpdate")
    public Integer newsUpdate(@RequestBody News news);


    /**
     * @Description 添加公告
     * @Author jkm
     * @Date 2020/7/21 19:12
     */
    @PostMapping("/newsAdd")
    public Integer newsAdd(@RequestBody News news);

    /**
     * @Description 根据标题分页模糊 公告栏查询
     * @Author jkm
     * @Date 2020/7/21 14:47
     */
    @PostMapping("/newsFuzzyQuery")
    ResultData newsFuzzyQuery(@RequestParam("title") String title, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

}
