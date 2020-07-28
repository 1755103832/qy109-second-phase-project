package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.User;
import com.aaa.pro.service.IProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company: com.aaa.jkm
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 18:34 2020/7/16
 * @description：
 */
@RestController
@Api(value = "用户",tags = "用户操作接口")
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IProjectService iProjectService;
    /**
     * @Author: jkm
     * @Description:    查询所有用户信息
     * @Date: 20:58 2020/7/21
     * @param: []
     * @return java.util.Map
     */
    @GetMapping ("/user/selectAllUser")
    @ApiOperation(value = "查询用户",notes = "查询用户接口")
    public Map selectAllUser(){
        return iProjectService.selectAllUser();
    }

    /**
     * @Author: jkm
     * @Description:    新增用户
     * @Date: 20:59 2020/7/27
     * @param: [user]
     * @return com.aaa.pro.base.ResultData
     */
    @PostMapping("/user/addUser")
    @ApiOperation(value = "添加用户",notes = "添加用户接口")
    public Boolean addUser(User user){
        return iProjectService.addUser(user);
    }
    /**
     * @Author: jkm
     * @Description:    修改用户信息
     * @Date: 20:06 2020/7/28
     * @param: [userId]
     * @return java.lang.Boolean
     */
    @ApiOperation(value = "删除用户",notes = "删除用户接口")
    @PostMapping("/deleteUserById")
    public Boolean deleteUserById( @RequestParam("userId") Long userId){
        return iProjectService.deleteUserById(userId);

    }

    /**
     * @Description: 批量删除用户
     * @Author: jkm
     * @Date: 2020/7/21 20:51
     * @Param: [ids, tokenId]
     * @return: com.aaa.pro.base.ResultData
     */
    @GetMapping("/delUser")
    public ResultData delUser(@RequestParam("ids") List<Long> ids){
        return iProjectService.delUser(ids);
    }



    /**
     * @Description: 修改用户信息
     * @Author: jkm
     * @Date: 2020/7/21 15:56
     * @Param: [user]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户",notes = "修改用户接口")
    public ResultData updateUser(@RequestBody User user){

        return iProjectService.updateUser(user);
    }


    /**
     * @Description: 导出用户信息
     * @Author: jkm
     * @Date: 2020/721 16:26
     * @Param: []
     * @return: com.aaa.pro.base.ResultData
     */
    @GetMapping("/exportExcel")
    public ResultData exportExcel(@RequestParam("response") HttpServletResponse response){
       return iProjectService.exportExcel(response);
    }

    /**
     * @Description: 带条件查询用户信息
     * @Author: jkm
     * @Date: 2020/7/21 22:31
     * @Param: [map]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/selectUserAll")
    public ResultData selectUserAll(@RequestBody Map map){
       return iProjectService.selectUserAll(map);
    }

}
