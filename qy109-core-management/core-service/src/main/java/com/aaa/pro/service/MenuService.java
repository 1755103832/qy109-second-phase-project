package com.aaa.pro.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.MenuMapper;
import com.aaa.pro.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 16:50
 * @Description
 **/
@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * @Author zyb
     * @Description 获取菜单信息
     * @Date 2020/7/21 17:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Menu>
     **/
    public List<Menu> selectAllMenus() {
        // 菜单树
        List<Menu> menuList = new ArrayList<>();
        // 菜单的全部信息
        List<Menu> allMenusList = menuMapper.selectAll();
        if (null != allMenusList && allMenusList.size() > 0) {
            // 拿到一级菜单信息
            for (int i = 0; i < allMenusList.size(); i++) {
                Menu menu = allMenusList.get(i);
                if (menu.getParentId() == 0) {
                    // 说明是一级菜单
                    menuList.add(menu);
                }
            }
            // 为一级菜单设置子菜单
            for (Menu menu : menuList) {
                menu.setSubMenu(getSubMenu(menu.getMenuId(), allMenusList));
            }
        }
        return menuList;
    }

    /**
     * @Author zyb
     * @Description 获取上一级菜单的子菜单
     * @Date 2020/7/21 19:35
     * @Param [menuId, allMenus]
     * @Return java.util.List<com.aaa.pro.model.Menu>
     **/
    private List<Menu> getSubMenu(Long menuId, List<Menu> allMenus) {
        // 子菜单
        List<Menu> subMenus = new ArrayList<>();
        for (Menu menu : allMenus) {
            if (menu.getParentId().equals(menuId)) {
                subMenus.add(menu);
            }
        }
        /*
         * 子菜单的下一级
         * 疑问：当递归进入，查找子菜单的子菜单，
         * 那么子菜单的数据现在在哪，是在下面循环代码中的subMenus中
         */
        for (Menu menu : subMenus) {
            menu.setSubMenu(getSubMenu(menu.getMenuId(), allMenus));
        }
        if (subMenus.size() == 0) {
            return null;
        }
        return subMenus;
    }

    /**
     * @Author zyb
     * @Description 新增菜单或者按钮
     * @Date 2020/7/21 19:44
     * @Param [menu]
     * @Return java.lang.Boolean
     **/
    public Boolean insertMenuOrButton(Menu menu) {
        String createTime = DateUtil.now();
        menu.setCreateTime(createTime);
        Integer add = super.insertSelective(menu);
        return add > 0;
    }

    /**
     * @Author zyb
     * @Description 根据主键修改菜单或者按钮的信息
     * @Date 2020/7/21 19:52
     * @Param [menu]
     * @Return java.lang.Boolean
     **/
    public Boolean updateMenuOrButton(Menu menu) {
        String time = DateUtil.now();
        menu.setModifyTime(time);
        Integer update = super.updateByPrimaryKeySelective(menu);
        return update > 0;
    }

    /**
     * @Author zyb
     * @Description 通过主键id删除菜单或者按钮
     * @Date 2020/7/21 19:55
     * @Param [menuId]
     * @Return java.lang.Boolean
     **/
    public Boolean deleteMenuOrButton(Long menuId) {
        int i = menuMapper.deleteByPrimaryKey(menuId);
        return i > 0;
    }

}
