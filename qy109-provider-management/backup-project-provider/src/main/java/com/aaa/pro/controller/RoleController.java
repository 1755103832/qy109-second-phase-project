package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.Role;
import com.aaa.pro.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * @return com.github.pagehelper.PageInfo
     * @Author: jkm
     * @Description: 查询所有角色信息
     * @Date: 18:12 2020/7/17
     * @param: [pageNo, pageSize]
     */
    @GetMapping("/selectAllRole")
    public PageInfo selectAllRole(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = roleService.selectAllRole(pageNo, pageSize);
        if (null != pageInfo) {
            return pageInfo;
        }
        return null;
    }

    /**
     * @return com.github.pagehelper.PageInfo
     * @Author: jkm
     * @Description: 根据条件查询
     * @Date: 18:11 2020/7/17
     * @param: [map, pageNo, pageSize]
     */
    @PostMapping("/selectRoleByField")
    public PageInfo selectRoleByField(@RequestBody Map map, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = roleService.selectRoleByField(map, pageNo, pageSize);
        if (!"".equals(pageInfo) && null != pageInfo) {
            return pageInfo;
        }
        return null;
    }

    /**
     * @return com.aaa.pro.model.Role
     * @Author: jkm
     * @Description: 根据主键查询角色信息
     * @Date: 18:11 2020/7/17
     * @param: [roleId]
     */
    @GetMapping("selectRoleByPrimaryKey")
    public Role selectRoleByPrimaryKey(@RequestParam("roleId") Long roleId) {
        Role role = roleService.selectRoleByParimaryKey(roleId);
        if (!"".equals(role) && null != role) {
            return role;
        }
        return null;
    }


    @Override
    public BaseService getBaseService() {
        return roleService;
    }
}
