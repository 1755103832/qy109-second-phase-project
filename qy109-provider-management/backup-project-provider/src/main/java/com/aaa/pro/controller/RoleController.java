package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Role;
import com.aaa.pro.service.RoleService;
import com.aaa.pro.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.pro.staticproperties.CodeFormatProperties.CODE;
import static com.aaa.pro.staticproperties.CodeFormatProperties.MSG;
import static com.aaa.pro.status.CrudStatus.*;

/**
 * @Company: com.aaa.jkm
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 10:41 2020/7/16
 * @description：
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 查询所有的角色
     * @Author: jkm
     * @Date: 2020/7/20 19:01
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
    public List<Role> selectRolesByUserId(String  id){
        Long userId = Long.valueOf(id);

        return roleService.selectRoleIdByUserId(userId);
    }



    /**
     * @Description: 简单的分页查询
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/pageRoles")
    public ResultData selectAllRoleByPage(RoleVo roleVo) {
        ResultData resultData = roleService.selectAllRoleByPage(roleVo);
        if (QUERY_SUCCESS_DATA.getCode() == resultData.getCode()) {
            return querySuccess(resultData.getData());
        } else {
            return queryFailed();
        }
    }

    /**
     * @Description: 删除角色
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: [roleId]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/deleteRole")
    public ResultData deleteRole(String roleId) {
        Long id = Long.valueOf(roleId);
        Boolean aBoolean = roleService.deleteRole(id);
        if (aBoolean) {
            return deleteSuccess();
        } else {
            return deleteFailed();
        }
    }

    /**
     * @Description: 新增角色以及批量新增权限
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/insertRole")
    public ResultData insertRole(RoleVo roleVo) {
        Boolean aBoolean = roleService.insertRole(roleVo);
        if (true == aBoolean) {
            return super.insertSuccess();
        } else {
            return super.insertFailed();
        }
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
    public Map addRole(Role role){
        Map map = new HashMap();

        Boolean aBoolean = roleService.insertRole(role);
        if (aBoolean){
            map.put(CODE,INSERT_DATA_SUCCESS.getCode());
            map.put(MSG,INSERT_DATA_SUCCESS.getMessage());
        }else {
            map.put(CODE,INSERT_DATA_FAILED.getCode());
            map.put(MSG,INSERT_DATA_FAILED.getMessage());
        }
        return map;

    }
    /**
     * @Description: 修改角色及其权限
     * @Author: jkm
     * @Date: 2020/7/20 19:01
     * @Param: [roleVo]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/updateRole")
    public ResultData updateRole(RoleVo roleVo) {
        Boolean aBoolean = roleService.updateRole(roleVo);
        if (aBoolean == true) {
            return updateSuccess();
        } else {
            return updateFailed();
        }
    }

}
