package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Dept;
import com.aaa.pro.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 部门管理
 */
@RestController
@Api(value = "部门管理", tags = "部门管理的接口")
public class DeptController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @description:
     *   递归查询根据 parentId（父id）查询该部门及其子部门
     * @params: [parentId]
     * @return: com.aaa.pro.base.ResultData<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 17:11
     */
    @GetMapping("/selectAllDeptByParentId")
    public ResultData<Dept> selectAllDeptByParentId(Long parentId) {
        // 调用iProjectService中的 selectAllDeptByParentId 方法，得到查询结果
        List<Dept> deptList = iProjectService.selectAllDeptByParentId(parentId);

        // 判断 结果是否为空
        if (deptList != null) {
            // 说明查询成功，使用系统消息
            return querySuccess(deptList);
        }else {
            // 查询失败，使用系统消息
            return queryFailed();
        }
    }

    /**
     * @description:
     *  查询部门信息-动态sql-查询条件：部门名称 创建时间区间
     * @params: [map]
     * @return: com.aaa.pro.base.ResultData<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 17:05
     */
    @PostMapping("/getDeptInfoByField")
    public ResultData<Dept> getDeptInfoByField(@RequestBody Map map) {
        // 调用 iProjectService 中的 selectDeptInfoByField 方法，得到查询结果
        List<Dept> deptList = iProjectService.selectDeptInfoByField(map);

        // 判断 结果是否为空
        if (deptList != null) {
            // 说明查询成功，使用系统消息
            return querySuccess(deptList);
        }else {
            // 查询失败，使用系统消息
            return queryFailed();
        }
    }

    /**
     * @description:
     *   查询部门信息，根据主键id查询部门的信息
     * @params: [deptId]
     * @return: com.aaa.pro.base.ResultData<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 17:05
     */
    @GetMapping("/getDeptByDeptId")
    public ResultData<Dept> getDeptByDeptId(Integer deptId) {
        // 调用 iProjectService 中的 selectDeptByDeptId 方法，得到查询结果
        Dept dept = iProjectService.selectDeptByDeptId(deptId);

        // 判断 结果是否为空
        if (dept != null) {
            // 说明查询成功，返回自定义信息
            return querySuccess(dept);
        }else {
            // 查询失败，使用系统消息
            return queryFailed();
        }
    }

    /**
     * @description:
     *   新增部门信息
     * @params: [dept]
     * @return: com.aaa.pro.base.ResultData<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 17:07
     */
    @PostMapping("/addDept")
    public ResultData<Dept> addDept(Dept dept) {
        // 调用 iProjectService 中的 insertDept 方法，得到添加结果
        Boolean aBoolean = iProjectService.insertDept(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明新增成功，使用系统消息
            return insertSuccess();
        }else {
            // 新增失败，使用系统消息
            return insertFailed();
        }
    }

    /**
     * @Author wxz
     * @Description 通过主键 执行删除操作
     * @Param
     * @return
     **/
    @PostMapping("/deleteDeptByPrimaryKey")
    public ResultData<Dept> deleteDeptByPrimaryKey(Dept dept) {
        // 调用 iProjectService 中的 deleteDeptByPrimaryKey 方法，得到删除结果
        Boolean aBoolean = iProjectService.deleteDeptByPrimaryKey(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明删除成功，使用系统消息
            return deleteSuccess();
        }else {
            // 删除失败，使用系统消息
            return deleteFailed();
        }
    }
    /**
     * @Author wxz
     * @Description 批量删除 调用父类的批量删除方法（根据主键），执行删除操作
     * @Param
     * @return
     **/
    @PostMapping("/batchDeleteByPrimaryKey")
    public ResultData<Dept> batchDeleteByPrimaryKey(List<Object> ids) {
        // 调用 iProjectService 中的 batchDeleteByPrimaryKey 方法，得到删除结果
        Boolean aBoolean = iProjectService.batchDeleteByPrimaryKey(ids);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明删除成功，使用系统消息
            return deleteSuccess();
        }else {
            // 删除失败，使用系统消息
            return deleteFailed();
        }
    }
    /**
     * @Author wxz
     * @Description 修改，通过主键-修改部门信息
     * @Param
     * @return
     **/
    @PostMapping("/updateDeptByPrimaryKey")
    public ResultData<Dept> updateDeptByPrimaryKey(Dept dept) {
        // 调用 iProjectService 中的 batchDeleteByPrimaryKey 方法，得到修改结果
        Boolean aBoolean = iProjectService.updateDeptByPrimaryKey(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明修改成功，使用系统消息
            return updateSuccess();
        }else {
            // 删除失败，使用系统消息
            return  updateFailed();
        }
    }





}
