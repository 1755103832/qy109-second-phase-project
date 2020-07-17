package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.RoleMapper;
import com.aaa.pro.model.Role;
import com.aaa.pro.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static com.aaa.pro.staticproperties.DateTimeFormatProperties.TIME_FORMAT;

/**
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.service
 * @Author： jkm
 * @Date： Create in 10:31 2020/7/16
 * @description： 角色的增删改查
 */
@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * @Author: jkm
     * @Description:
     *      查询所有角色信息
     * @Date: 11:16 2020/7/17
     * @param: [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    public PageInfo selectAllRole(Integer pageNo,Integer pageSize){
        // 当前页数和一页数量
        PageHelper.startPage(pageNo,pageSize);
         //查询权限信息
        try {
            List<Role> roles = roleMapper.selectAll();
            // 判断查询结果是否为空
            if (!"".equals(roles) && null != roles){
                PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
                // 返回结果
                return rolePageInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: jkm
     * @Description:
     *      条件查询 角色信息
     * @Date: 11:19 2020/7/17
     * @param: [map, pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    public PageInfo selectRoleByField(Map map,Integer pageNo, Integer pageSize){
        // 当前页数和一页数量
        PageHelper.startPage(pageNo,pageSize);
        //查询权限信息
        try {
            List<Role> roles = roleMapper.selectRoleByField(map);
            // 判断查询结果是否为空
            if (!"".equals(roles) && null != roles){
                PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
                // 返回结果
                return rolePageInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: jkm
     * @Description:
     *          根据id查询角色信息
     * @Date: 11:40 2020/7/17
     * @param: [roleId]
     * @return com.aaa.pro.model.Role
     */
    public Role selectRoleByParimaryKey(Long roleId){
        // 判断前端是否传值成功
        if (null != roleId){
            // 根据id查询
            try {
                Role role = roleMapper.selectByPrimaryKey(roleId);
                // 判断是否查询成功
                if (!"".equals(role) && null != role){
                    // 返回查询信息
                    return role;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Author: jkm
     * @Description:
     *      新增角色和对应权限
     * @Date: 14:38 2020/7/17
     * @param: [role]
     * @return java.lang.Long
     */
    public Long insertRole(Role role){
        // 判断前端是否传值成功
        // 获取当前时间
        // 设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
        if (!"".equals(role) && null != role){
            try {
                role.setRoleName(role.getRoleName())
                    .setRemark(role.getRemark())
                    .setCreateTime(simpleDateFormat.parse(DateUtils.getCurrentDate()));
                // 执行新增操作
                Integer integer = roleMapper.insertRoleResultId(role);
                @NotNull Long roleId = role.getRoleId();
                if (null != integer ){
                    return roleId;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Author: jkm
     * @Description:
     *      根据id修改角色
     * @Date: 16:05 2020/7/17
     * @param: [role]
     * @return java.lang.Integer
     */
    public Integer updateRoleByPrimaryKey(Role role){
        // 判断前端是否传值成功
        if (!"".equals(role) && null !=role){

            try {
                //将当前时间传入修改时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
                try {
                    role.setModifyTime(simpleDateFormat.parse(DateUtils.getCurrentDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //调用修改方法
                int i = roleMapper.updateRoleByPrimaryKey(role);
                //判断是否修改成功
                if (i>0 ){
                    //返回受影响的行数
                    return i;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * @Author: jkm
     * @Description:
     *      根据 roleTds 批量删除角色
     * @Date: 16:17 2020/7/17
     * @param: [roleIds]
     * @return java.lang.Boolean
     */
    public Boolean deleteRoleByRoleIds(List<Object> roleIds){
        //判断前段是否传值成功
        if (!"".equals(roleIds) && null !=roleIds){
            try {
                //调用父类重载的批量删除方法
                Integer integer = super.batchDeleteByRoleIds(roleIds);
                if (integer>0){
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

