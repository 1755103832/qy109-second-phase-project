package com.aaa.pro.mapper;

import com.aaa.pro.model.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends Mapper<Role> {
    /**
     *  根据字段属性查询角色
     * @param map
     * @return
     */
    List<Role> selectRoleByField(Map map);

    /**
     *  新增角色
     * @param role
     * @return
     */
    Integer insertRoleResultId(Role role);

    /**
     *  修改角色信息
     * @param role
     * @return
     */
    Integer updateRoleByPrimaryKey(Role role);
}