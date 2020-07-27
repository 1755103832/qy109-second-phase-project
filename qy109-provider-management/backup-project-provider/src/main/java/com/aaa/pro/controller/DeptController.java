package com.aaa.pro.controller;

import com.aaa.pro.model.Dept;
import com.aaa.pro.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统管理--部门管理
 */
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * @description:
     *  查询部门信息，根据主键id查询
     * @params: [deptId]
     * @return: com.aaa.pro.model.Dept
     * @author: Wen
     * @date: 2020/7/16 8:59
     */
    @GetMapping("/selectDeptByDeptId")
    public Dept selectDeptByDeptId(@RequestParam("deptId") Integer deptId) {
        // 调用 deptService 中的 selectDeptByDeptId 方法，得到查询结果
        Dept dept = deptService.selectDeptByDeptId(deptId);

        // 判断 结果是否为空
        if (dept != null) {
            // 说明结果不为空，返回查询的结果
            return dept;
        }else {
            // 返回null
            return null;
        }
    }


    /**
     * @description: 新增部门信息
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/14 9:00
     */
    @PostMapping("/insertDept")
    public Boolean insertDept(@RequestBody Dept dept) {
        // 调用 deptService 中的 insertDept 方法，得到结果
        Boolean aBoolean = deptService.insertDept(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明结果为true，新增成功，返回true
            return true;
        }else {
            // 新增失败，返回false
            return false;
        }
    }


    /**
     * @description:
     *   删除部门信息，根据主键删除
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/14 9:00
     */
    @PostMapping("/deleteDeptByPrimaryKey")
    public Boolean deleteDeptByPrimaryKey(@RequestBody Dept dept) {
        // 调用 deptService 中的 deleteDeptByPrimaryKey 方法，得到结果
        Boolean aBoolean = deptService.deleteDeptByPrimaryKey(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明结果为true，删除成功 返回true
            return true;
        }else {
            // 删除失败，返回false
            return false;
        }
    }

    /**
     * @description:
     *   批量删除部门信息，调用父类的批量删除方法（根据主键删除）
     * @params: [ids]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 9:01
     */
    @PostMapping("/batchDeleteByPrimaryKey")
    public Boolean batchDeleteByPrimaryKey(@RequestBody List<Object> ids) {
        // 调用 deptService 中的 batchDeleteByPrimaryKey 方法，得到结果
        Boolean aBoolean = deptService.batchDeleteByPrimaryKey(ids);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明结果为true，删除成功 返回true
            return true;
        }else {
            // 删除失败，返回false
            return false;
        }
    }


    /**
     * @description:
     *   修改部门信息，根据主键修改
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 9:01
     */
    @PostMapping("/updateDeptByPrimaryKey")
    public Boolean updateDeptByPrimaryKey(@RequestBody Dept dept) {
        // 调用 deptService 中的 updateDeptByPrimaryKey 方法，得到结果
        Boolean aBoolean = deptService.updateDeptByPrimaryKey(dept);

        // 判断 结果是否为true
        if (aBoolean == true) {
            // 说明结果为true，修改成功 返回true
            return true;
        }else {
            // 修改失败，返回false
            return false;
        }
    }

    /**
     * @description:
     *    查询子菜单(部门)
     * @params: [parentId]
     * @return: java.util.List<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 16:43
     */
    @GetMapping("/selectAllDeptByParentId")
    public List<Dept> selectAllDeptByParentId(@RequestParam("parentId") Integer parentId) {
        // 调用 deptService 中的 selectAllDeptByParentId 方法，得到查询结果
        List<Dept> allDept = deptService.selectAllDeptByParentId(parentId);

        // 判断 结果是否为空
        if (allDept != null) {
            // 说明结果不为空，返回查询的结果
            return allDept;
        }else {
            // 返回null
            return null;
        }
    }

    /**
     * @description:
     *  查询-动态sql查询-条件：部门名称 创建时间区间
     * @params: [map]
     * @return: java.util.List<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/16 16:43
     */
    @PostMapping("/selectDeptInfoByField")
    public List<Dept> selectDeptInfoByField(@RequestBody Map map) {
        // 调用 deptService 中的 selectDeptInfoByField 方法，得到查询结果
        List<Dept> deptList = deptService.selectDeptInfoByField(map);

        // 判断 结果是否为空
        if (deptList != null) {
            // 说明结果不为空，查询成功，返回查询的结果
            return deptList;
        }else {
            // 查询失败，返回null
            return null;
        }
    }


}
