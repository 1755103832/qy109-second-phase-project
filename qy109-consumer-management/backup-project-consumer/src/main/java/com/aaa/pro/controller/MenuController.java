package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Menu;
import com.aaa.pro.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 16:51
 * @Description
 **/
@RestController
@Api(tags = "菜单管理接口")
public class MenuController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * @Author zyb
     * @Description 获取菜单信息
     * @Date 2020/7/21 17:32
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    @GetMapping("/selectAllMenus")
    @ApiOperation(value = "获取菜单信息")
    public ResultData selectAllMenus() {
        return projectService.selectAllMenus();
    }

    /**
     * @Author zyb
     * @Description 新增菜单或者按钮
     * @Date 2020/7/22 12:04
     * @Param [menu]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/insertMenuOrButton")
    @ApiOperation(value = "新增菜单或者按钮")
    public ResultData insertMenuOrButton(@RequestBody Menu menu) {
        return projectService.insertMenuOrButton(menu);
    }

    /**
     * @Author zyb
     * @Description 根据主键修改菜单或者按钮的信息
     * @Date 2020/7/21 19:52
     * @Param [menu]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/updateMenuOrButton")
    @ApiOperation(value = "根据主键修改菜单或者按钮的信息")
    public ResultData updateMenuOrButton(@RequestBody Menu menu) {
        return projectService.updateMenuOrButton(menu);
    }

    /**
     * @Author zyb
     * @Description 通过主键id删除菜单或者按钮
     * @Date 2020/7/21 19:55
     * @Param [menuId]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/deleteMenuOrButton")
    @ApiOperation(value = "通过主键id删除菜单或者按钮")
    public ResultData deleteMenuOrButton(@RequestParam("menuId") Long menuId) {
        return projectService.deleteMenuOrButton(menuId);
    }

}
