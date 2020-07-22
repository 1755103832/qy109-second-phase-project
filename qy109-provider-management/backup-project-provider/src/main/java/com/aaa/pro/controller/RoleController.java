package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.service.RoleService;
import com.aaa.pro.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.aaa.pro.status.CrudStatus.QUERY_SUCCESS_DATA;

/**
 * @Company: com.aaa.jkm
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 10:41 2020/7/16
 * @description：
 */
@RestController
public class RoleController extends CommonController {

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 查询所有的角色
     * @Author: sgz
     * @Date: 2020/6/3 19:01
     * @Param: []
     * @return: com.aaa.pro.base.ResultData
     */
    @GetMapping("/allRoles")
    public ResultData selectAllRole() {
        ResultData resultData = roleService.selectAllRole();
        if (QUERY_SUCCESS_DATA.getCode() == resultData.getCode()) {
            return querySuccess(resultData.getData());
        } else {
            return queryFailed();
        }
    }

    /**
     * @Description: 简单的分页查询
     * @Author: sgz
     * @Date: 2020/6/3 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/pageRoles")
    public ResultData selectAllRoleByPage(@RequestBody RoleVo roleVo) {
        ResultData resultData = roleService.selectAllRoleByPage(roleVo);
        if (QUERY_SUCCESS_DATA.getCode() == resultData.getCode()) {
            return querySuccess(resultData.getData());
        } else {
            return queryFailed();
        }
    }

    /**
     * @Description: 删除角色
     * @Author: sgz
     * @Date: 2020/6/3 19:01
     * @Param: [roleId]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/deleteRole")
    public ResultData deleteRole(@RequestParam("roleId") Long roleId) {
        Boolean aBoolean = roleService.deleteRole(roleId);
        if (aBoolean == true) {
            return deleteSuccess();
        } else {
            return deleteFailed();
        }
    }

    /**
     * @Description: 新增角色以及批量新增权限
     * @Author: sgz
     * @Date: 2020/6/3 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/insertRole")
    public ResultData insertRole(@RequestBody RoleVo roleVo) {
        Boolean aBoolean = roleService.insertRole(roleVo);
        if (true == aBoolean) {
            return super.insertSuccess();
        } else {
            return super.insertFailed();
        }
    }

    /**
     * @Description: 修改角色及其权限
     * @Author: sgz
     * @Date: 2020/6/3 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/updateRole")
    public ResultData updateRole(@RequestBody RoleVo roleVo) {
        Boolean aBoolean = roleService.updateRole(roleVo);
        if (aBoolean == true) {
            return updateSuccess();
        } else {
            return updateFailed();
        }
    }

    @Override
    public BaseService getBaseService() {
        return roleService;
    }
}
