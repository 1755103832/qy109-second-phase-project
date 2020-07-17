package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Role;
import com.aaa.pro.service.IProjectService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 16:27 2020/7/17
 * @description： 角色管理增删改查
 */
@RestController
@Api(value = "角色管理", tags = "角色管理接口")
public class RoleController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @return com.aaa.pro.base.ResultData
     * @Author: jkm
     * @Description: 查询所有角色信息
     * @Date: 17:40 2020/7/17
     * @param: [pageNo, pageSize]
     */
    @GetMapping("selectAllRole")
    public ResultData selectAllRole(Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = iProjectService.selectAllRole(pageNo, pageSize);
        //判断是否查询成功
        if (!"".equals(pageInfo) && null != pageInfo) {
            return super.querySuccess(pageInfo);
        }
        return super.queryFailed();
    }


    /**
     * @return com.aaa.pro.base.ResultData
     * @Author: jkm
     * @Description: 根据条件查询角色
     * @Date: 17:40 2020/7/17
     * @param: [map, pageNo, pageSize]
     */
    @PostMapping("selectRoleByField")
    public ResultData selectRoleByField(@RequestBody Map map, Integer pageNo, Integer pageSize) {
        PageInfo pageInfo = iProjectService.selectRoleByField(map, pageNo, pageSize);
        //判断是否查询成功
        if (!"".equals(pageInfo) && null != pageInfo) {
            return super.querySuccess(pageInfo);
        }
        return super.queryFailed();
    }

    /**
     * @return com.aaa.pro.base.ResultData
     * @Author: jkm
     * @Description: 根据主键查询信息
     * @Date: 17:32 2020/7/17
     * @param: [roleId]
     */
    @GetMapping("selectRoleByPrimaryKey")
    public ResultData selectRoleByPrimaryKey(Long roleId) {
        Role role = iProjectService.selectRoleByPrimaryKey(roleId);
        if (!"".equals(role) && null != role) {
            return super.querySuccess(role);
        }
        return super.queryFailed();
    }

}
