package com.aaa.pro.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.mapper.RoleMapper;
import com.aaa.pro.mapper.RoleMenuMapper;
import com.aaa.pro.mapper.UserRoleMapper;
import com.aaa.pro.model.Role;
import com.aaa.pro.model.RoleMenu;
import com.aaa.pro.model.UserRole;
import com.aaa.pro.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.aaa.pro.status.CrudStatus.QUERY_FIND_NOT_DATA;
import static com.aaa.pro.status.CrudStatus.QUERY_SUCCESS_DATA;

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
    RoleMapper roleMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * @Author: Sgz
     * @Time: 10:25 2020/7/23
     * @Params: [id]
     * @Return: java.util.List
     * @Throws:
     * @Description: 通过userId查询该用户所对应的角色 获取角色列表
     */

    public List<Role> selectRoleIdByUserId(Long userId) {
        UserRole userRole = new UserRole();
        Role role = new Role();
        userRole.setUserId(userId);
        // 判断有没有拿到userId
        if ("".equals(userId) || userId == null) {
            return null;
        } else {
            // 当拿到userId 判断有么有获取到对应的RoleIDs
            List<UserRole> userRoleIds = userRoleMapper.select(userRole);
            // 如果拿到了数据 就通过rolId获取对应的roleName
            // 通过循环拿到userRole对象
            if (userRoleIds.size() > 0) {
                for (UserRole userRoleId : userRoleIds) {
                    // 从对象中获取roleId
                    Long roleId = userRoleId.getRoleId();
                    // 把RoleId给role对象
                    role.setRoleId(roleId);
                    // 通过role对象获取到roleList
                    List<Role> roleList = roleMapper.select(role);
                    if (roleList.size() > 0) {
                        return roleList;
                    }


                }
            }
            return null;
        }

    }

    /**
     * @Description: 查询所有的角色
     * @Author: jkm
     * @Date: 2020/6/3 19:02
     * @Param: []
     * @return: com.aaa.qy108.base.ResultData
     */
    public ResultData selectAllRole() {
        ResultData resultData = new ResultData();
        List<Role> roles = roleMapper.selectAll();
        if (null == roles || roles.size() <= 0) {
            //说明没有查到 查到为空
            resultData.setCode(QUERY_FIND_NOT_DATA.getCode());
            resultData.setMessage(QUERY_FIND_NOT_DATA.getMessage());
        } else {
            resultData.setCode(QUERY_SUCCESS_DATA.getCode());
            resultData.setMessage(QUERY_SUCCESS_DATA.getMessage());
            resultData.setData(roles);
        }
        return resultData;
    }

    /**
     * @Description: 简单的分页查询
     * @Author: jkm
     * @Date: 2020/6/3 19:02
     * @Param: [roleVo]
     * @return: com.aaa.qy108.base.ResultData
     */
    public ResultData selectAllRoleByPage(RoleVo roleVo) {
        ResultData resultData = new ResultData();
        try {
            PageInfo<Role> rolePageInfo = super.selectListByPage(roleVo.getRole(), roleVo.getPageNo(), roleVo.getPageSize());
            if (null == rolePageInfo || "".equals(rolePageInfo)) {
                //说明没有查到
                resultData.setCode(QUERY_FIND_NOT_DATA.getCode());
                resultData.setMessage(QUERY_FIND_NOT_DATA.getMessage());
            } else {
                resultData.setCode(QUERY_SUCCESS_DATA.getCode());
                resultData.setMessage(QUERY_SUCCESS_DATA.getMessage());
                resultData.setData(rolePageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }


    /**
     * @Description: 删除角色
     * @Author: jkm
     * @Date: 2020/6/3 19:02
     * @Param: [roleId]
     * @return: java.lang.Boolean
     */
    public Boolean deleteRole(Long roleId) {
        int i = roleMapper.deleteByPrimaryKey(roleId);
        if (i > 0) {
            //说明删除成功
            // 接下来要去把role_menu表中对应的数据删掉
            //先去查他有没有权限 有权限就全部删掉  没有就结束
            List<RoleMenu> list = roleMenuMapper.selectByRoleId(roleId);
            if (list.size() > 0) {
                //说明权限不是是空的  需要删除
                int i1 = roleMenuMapper.deleteRoleMenu(roleId);
                //说明删除成功
                //删除失败
                return i1 > 0;
            } else {
                //说明权限是空的  不要删除
                return true;
            }
        } else {
            //删除失败  直接false
            return false;
        }

    }

    /**
     * @Author: Sgz
     * @Time: 17:27 2020/7/24
     * @Params: [role]
     * @Return: java.lang.Boolean
     * @Throws:
     * @Description: 根据roleId决定添加、更新
     * 如果roleId存在 就说明此角色已经存在 此时应该是修改操作
     * 如果roleId不存在 则说明role不存在 此时进行添加操作
     */
    public Boolean insertRole(Role role) {
        // 利用工具类创建现在的时间
        String s = DateUtil.now();


        // 判断role是否存在 如果role不存在 说明没有拿到数据
        if ("".equals(role) || role == null) {
            return false;
        } else {
            // 如果没有拿到roleId 说明此时时进行添加操作
            if (role.getRoleId() == null) {
                // 把创建时间存入到role中
                role.setCreateTime(s);
                // 判断是否 添加成功 i>0说明添加成功
                int i = roleMapper.insert(role);
                return i > 0;
            } else {
                // 把更新时间存入到role中
                role.setModifyTime(s);
                // 判断是否更新成功
                int i = roleMapper.updateByPrimaryKey(role);
                return i > 0;
            }
        }
    }


    /**
     * @Description: 新增角色以及批量新增权限
     * @Author: jkm
     * @Date: 2020/6/3 19:02
     * @Param: [roleVo]
     * @return: java.lang.Boolean
     */
    public Boolean insertRole(RoleVo roleVo) {
        String s = DateUtil.now();
        roleVo.getRole().setCreateTime(s);

        int insert = roleMapper.insert(roleVo.getRole());
        if (insert > 0) {
            //说明新增成功了
            //那就开始加roleMenu
            //如果传过来的menuId是null 说明不添加了
            //如果传来的不是空  说明需要添加roleMenu
            if (null == roleVo.getMenuId() || "".equals(roleVo.getMenuId())) {
                //说明不添加权限  只是加一个角色 返回true结束
                return true;
            } else {
                //说明需要添加新的菜单权限
                List<RoleMenu> list = new ArrayList();
                for (Long menuId : roleVo.getMenuId()) {
                    RoleMenu rm = new RoleMenu();
                    rm.setMenuId(menuId);
                    rm.setRoleId(roleVo.getRole().getRoleId());
                    list.add(rm);
                }
                Integer i = roleMenuMapper.batchInsertRoleMenu(list);
                //说明批量新增也成功了  那就返回
                return i > 0;
            }
        }
        //新增失败直接false
        return false;
    }


    /**
     * @Description: 修改角色及其权限
     * @Author: jkm
     * @Date: 2020/6/3 19:02
     * @Param: [roleVo]
     * @return: java.lang.Boolean
     */
    public Boolean updateRole(RoleVo roleVo) {
        String s = DateUtil.now();
        roleVo.getRole().setModifyTime(s);
        //1、去修改role表
        int i = roleMapper.updateByPrimaryKeySelective(roleVo.getRole());
        if (i > 0) {
            //1、说明role表修改成功
            //2、要继续去修改Role的Menu
            //3、如果是没有动权限表  不执行以下流程     （在这咋判断有没有新权限）
            //4、修改就是 先删完老的， 再加新的  不管是换新的权限还是权限全部取消 都先删除 (在这判断传过来的menuId是不是null)
            //5、如果他之前没有权限就不能去删除  直接新增
            //6、如果他有权限，要撤销他的权限  就直接删除即可
            List<RoleMenu> list = roleMenuMapper.selectByRoleId(roleVo.getRole().getRoleId());
            boolean equals = list.equals(roleVo.getMenuId());
            if (true == equals) {
                //说明没有改动权限表  直接返回true
                return true;
            } else {
                //说明要改动权限表  那就先查他之前是否有权限
                List<RoleMenu> menus = roleMenuMapper.selectByRoleId(roleVo.getRole().getRoleId());
                if (menus.size() > 0) {
                    //说明以前是有权限的  无论是要给他撤销全部的权限  还是要更改他的权限  都先全部删除
                    int i2 = roleMenuMapper.deleteRoleMenu(roleVo.getRole().getRoleId());
                    if (i2 > 0) {
                        //说明权限已经全部删除了   接下来判断是否要给他换上新的权限
                        //如果传进来的权限是空的
                        if (null == roleVo.getMenuId() || "".equals(roleVo.getMenuId())) {
                            //说明没有新的权限 那就结束
                            return true;
                        } else {
                            List<RoleMenu> arr = new ArrayList();
                            for (Long jkmd : roleVo.getMenuId()) {
                                RoleMenu rm = new RoleMenu();
                                rm.setMenuId(jkmd);
                                rm.setRoleId(roleVo.getRole().getRoleId());
                                arr.add(rm);
                            }
                            int i3 = roleMenuMapper.batchInsertRoleMenu(arr);
                            //说明修改彻底结束
                            return i3 > 0;
                        }
                    }
                }
            }
        }
        return false;
    }
}

