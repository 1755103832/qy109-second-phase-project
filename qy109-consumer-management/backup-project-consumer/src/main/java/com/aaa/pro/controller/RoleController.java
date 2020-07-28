package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Role;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.pro.staticproperties.CodeFormatProperties.CODE;
import static com.aaa.pro.staticproperties.CodeFormatProperties.MSG;
import static com.aaa.pro.status.CrudStatus.*;
import static com.aaa.pro.status.CrudStatus.INSERT_DATA_FAILED;

/**
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 16:27 2020/7/17
 * @description： 角色管理增删改查
 */
@RestController
@Api(value = "角色管理", tags = "角色管理接口")
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Description: 查询所有的角色
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: []
     * @return: com.aaa.pro.base.ResultData
     */
    @GetMapping("/allRoles")
    @ApiOperation(value = "查询所有角色",notes = "角色管理中的查询所有角色")
    public ResultData selectAllRole() {
       return iProjectService.selectAllRole();
    }

    /**
     * @Author: jkm
     * @Time: 10:28 2020/7/23
     * @Params: [id]
     * @Return: java.util.List
     * @Throws:
     * @Description:
     * 通过userID获取对应的角色
     *
     */
    @GetMapping ("/selectRolesByUserId")
    public List<Role> selectRolesByUserId(@RequestParam("id") String  id){
        return iProjectService.selectRolesByUserId(id);
    }



    /**
     * @Description: 简单的分页查询
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/pageRoles")
    public ResultData selectAllRoleByPage(@RequestBody RoleVo roleVo) {
       return iProjectService.selectAllRoleByPage(roleVo);
    }

    /**
     * @Description: 删除角色
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: [roleId]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/deleteRole")
    @ApiOperation(value = "删除角色",notes = "角色管理中的删除角色")
    public ResultData deleteRole(@RequestParam("roleId") String roleId) {
       return iProjectService.deleteRole(roleId);
    }

    /**
     * @Description: 新增角色以及批量新增权限
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/insertRole")
    public ResultData insertRole(@RequestBody RoleVo roleVo) {
       return iProjectService.insertRole(roleVo);
    }

    /**
     * @Author: jkm
     * @Time: 17:31 2020/7/24
     * @Params: [role]
     * @Return: java.util.Map
     * @Throws:
     * @Description:
     * 通过获取到role 添加角色信息
     *
     */
    @PostMapping("/addRole")
    public Map addRole(@RequestBody Role role){

    return iProjectService.addRole(role);
    }
    /**
     * @Description: 修改角色及其权限
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @ApiOperation(value = "修改角色", notes = "角色管理中的修改角色")
    @PostMapping("/updateRole")
    public ResultData updateRole(@RequestBody RoleVo roleVo) {
       return iProjectService.updateRole(roleVo);
    }

}
