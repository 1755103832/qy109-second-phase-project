package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Menu;
import com.aaa.pro.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 16:50
 * @Description
 **/
@RestController
public class MenuController extends CommonController<Menu> {

    @Autowired
    private MenuService menuService;

    /**
     * getBaseService
     *
     * @return
     */
    @Override
    public BaseService<Menu> getBaseService() {
        return menuService;
    }

    /**
     * @Author zyb
     * @Description 获取菜单信息
     * @Date 2020/7/21 17:32
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/selectAllMenus")
    public ResultData selectAllMenus() {
        List<Menu> menuList = menuService.selectAllMenus();
        return menuList.size() > 0 ? super.querySuccess(menuList) : super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 新增菜单或者按钮
     * @Date 2020/7/22 12:04
     * @Param [menu]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/insertMenuOrButton")
    public ResultData insertMenuOrButton(@RequestBody Menu menu) {
        Boolean aBoolean = menuService.insertMenuOrButton(menu);
        return aBoolean ? super.insertSuccess(menu) : super.insertFailed();
    }

    /**
     * @Author zyb
     * @Description 根据主键修改菜单或者按钮的信息
     * @Date 2020/7/21 19:52
     * @Param [menu]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/updateMenuOrButton")
    public ResultData updateMenuOrButton(@RequestBody Menu menu) {
        Boolean aBoolean = menuService.updateMenuOrButton(menu);
        return aBoolean ? super.updateSuccess(menu) : super.updateFailed();
    }

    /**
     * @Author zyb
     * @Description 通过主键id删除菜单或者按钮
     * @Date 2020/7/21 19:55
     * @Param [menuId]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/deleteMenuOrButton")
    public ResultData deleteMenuOrButton(@RequestParam("menuId") Long menuId) {
        Boolean aBoolean = menuService.deleteMenuOrButton(menuId);
        return aBoolean ? super.deleteSuccess(menuId) : super.deleteFailed();
    }
}
