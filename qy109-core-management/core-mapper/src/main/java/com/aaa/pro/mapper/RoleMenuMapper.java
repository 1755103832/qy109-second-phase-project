package com.aaa.pro.mapper;

import com.aaa.pro.model.RoleMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 17:11
 * @Description 角色菜单表
 **/
public interface RoleMenuMapper extends Mapper<RoleMenu> {

    /**
     * @Author zyb
     * @Description 在RoleMenu表中根据roleId删除
     * @Date 2020/7/21 17:23
     * @Param [roleId]
     * @Return int
     **/
    int deleteRoleMenu(Long roleId);

    /**
     * @Author zyb
     * @Description 批量新增
     * @Date 2020/7/21 17:23
     * @Param [roleMenus]
     * @Return int
     **/
    int batchInsertRoleMenu(List<RoleMenu> roleMenus);

    /**
     * @Author zyb
     * @Description 查询表里面有没有roleId
     * @Date 2020/7/21 17:24
     * @Param [roleId]
     * @Return java.util.List<com.aaa.pro.model.RoleMenu>
     **/
    List<RoleMenu> selectByRoleId(Long roleId);

}