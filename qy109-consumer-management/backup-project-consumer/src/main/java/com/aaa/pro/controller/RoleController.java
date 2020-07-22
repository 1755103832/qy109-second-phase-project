package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.vo.RoleVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 16:27 2020/7/17
 * @description： 角色管理增删改查
 */
@RestController
//@Api(value = "角色管理", tags = "角色管理接口")
public class RoleController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Description: 简单的分页查询
     * @Author: sgz
     * @Date: 2020/6/3 18:51
     * @Param: [roleVo]
     * @return:
     */
    @PostMapping("/pageRoles")
    @ApiOperation(value = "角色信息", notes = "查询所有角色带分页的功能")
    public ResultData selectAllRoleByPage(@RequestBody RoleVo roleVo) {
        return iProjectService.selectAllRoleByPage(roleVo);
    }

    /**
     * @Description: 删除角色
     * @Author: sgz
     * @Date: 2020/6/3 18:51
     * @Param: [roleId]
     * @return:
     */
    @PostMapping("/deleteRole")
    @ApiOperation(value = "删除角色", notes = "删除角色的功能")
    public ResultData deleteRole(@RequestParam("roleId") Long roleId) {
        return iProjectService.deleteRole(roleId);
    }

    /**
     * @Description: 新增角色以及批量新增权限
     * @Author: sgz
     * @Date: 2020/6/3 18:52
     * @Param: [roleVo]
     * @return:
     */
    @PostMapping("/insertRole")
    @ApiOperation(value = "新增角色", notes = "新增角色的功能")
    public ResultData insertRole(@RequestBody RoleVo roleVo) {
        return iProjectService.insertRole(roleVo);
    }


    /**
     * @Description: 修改角色及其权限
     * @Author: sgz
     * @Date: 2020/6/3 18:52
     * @Param: [roleVo]
     * @return:
     */
    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色", notes = "修改角色的功能")
    public ResultData updateRole(@RequestBody RoleVo roleVo) {
        return iProjectService.updateRole(roleVo);
    }

}
