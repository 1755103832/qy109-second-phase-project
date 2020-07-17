package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.User;
import com.aaa.pro.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company: com.aaa.jkm
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 10:39 2020/7/16
 * @description：
 *      用户的数据返回
 */
@RestController
public class UserController extends CommonController<User> {
    @Autowired
    private UserService userService;

    @Override
    public BaseService<User> getBaseService() {
        return userService;
    }

    /**
     * @Author: jkm
     * @Description:
     *      查询所有用户
     * @Date: 17:11 2020/7/16
     * @param: [pageNo, pageSize]
     * @return com.github.pagehelper.PageInfo
     */
    @PostMapping("/selectAllUser")
    public PageInfo selectAllUser(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        return userService.selectAllUser(pageNo,pageSize);
    }

    /**
     * @Author: jkm
     * @Description:
     *      根据用户 id查询用户
     * @Date: 17:14 2020/7/16
     * @param: [id]
     * @return com.aaa.pro.model.User
     */
    @PostMapping("/selectUserById")
    public User selectUserById(@RequestParam("id") Long id){
        return userService.selectUserById(id);
    }

    /**
     * @Author: jkm
     * @Description:
     *          添加用户
     * @Date: 17:25 2020/7/16
     * @param: [user]
     * @return java.lang.Boolean
     */
    @PostMapping("/addUser")
    public Boolean addUser(@RequestBody User user){
        try {
            userService.selectListByPage(user,29,7);
        }catch(Exception e){
            e.printStackTrace();
        }
        return userService.addUser(user);
    }

    /**
     * @Author: jkm
     * @Description:
     *      根据主键删除用户
     * @Date: 17:33 2020/7/16
     * @param: [user]
     * @return java.lang.Integer
     */
    @PostMapping("/deleteUser")
    public Integer deleteUser(@RequestBody User user){
        try{
            Integer deleteUser = userService.deleteUser(user);
            return deleteUser;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: jkm
     * @Description:
     *      根据id批量删除
     * @Date: 17:40 2020/7/16
     * @param: [ids]
     * @return java.lang.Integer
     */
    @PostMapping("/deleteMoreUser")
    public Integer deleteMoreUser(@RequestBody List<Object> ids){
       return userService.deleteMoreUser(ids);
    }

    /**
     * @Author: jkm
     * @Description:
     *          根据id修改用户信息
     * @Date: 17:47 2020/7/16
     * @param: [user]
     * @return java.lang.Integer
     */
    @PostMapping("/updateUserById")
    public Integer updateUserById(@RequestBody User user){
      return userService.updateUserById(user);
    }
}
