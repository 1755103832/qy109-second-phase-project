package com.aaa.pro.mapper;

import com.aaa.pro.model.RoleMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: jkm
 * @Description: 角色菜单表
 */
public interface RoleMenuMapper extends Mapper<RoleMenu> {
    /**
     * 在RoleMenu表中根据roleId删除
     *
     * @param roleId
     * @return
     */
    int deleteRoleMenu(Long roleId);

    /**
     * 批量新增
     *
     * @param roleMenus
     * @return
     */
    int batchInsertRoleMenu(List<RoleMenu> roleMenus);

    /**
     * @Description: 查询表里面有没有roleId
     * @Author: jkm
     * @Date: 2020/6/3 19:03
     * @Param: [roleId]
     * @return: java.util.List<com.aaa.pro.model.RoleMenu>
     */
    List<RoleMenu> selectByRoleId(@Param("roleId") Long roleId);
}