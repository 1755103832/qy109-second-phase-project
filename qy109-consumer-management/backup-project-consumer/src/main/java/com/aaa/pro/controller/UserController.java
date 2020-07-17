package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.User;
import com.aaa.pro.service.IProjectService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
public class UserController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author: jkm
     * @Description: 添加用户，返回消息
     * @Date: 18:53 2020/7/16
     * @param: [user, request]
     * @return com.aaa.pro.base.ResultData
     */
    @PostMapping("/addUser")
    public ResultData addUser(@RequestBody User user, HttpServletRequest request){
        // 判断是否添加成功
        // 如果成功了就会返回系统成功code 系统消息
        if (iProjectService.addUser(user)){
            return insertSuccess(user);
        }
        //如果失败了就会返回系统失败code 系统消息
        else {
            return insertFailed();
        }
    }

    /**
     * @Author: jkm
     * @Description:
     *      查询所有用户
     * @Date: 19:17 2020/7/16
     * @param: [pageNo, pageSize]
     * @return com.aaa.pro.base.ResultData
     */
    @PostMapping("/selectAllUser")
    public ResultData selectAllUser(Integer pageNo, Integer pageSize){
        PageInfo pageInfo = iProjectService.selectAllUser(pageNo, pageSize);
        // 判断是否查询成功，成功返回成功code，系统消息
        if (!"".equals(pageInfo) && null != pageInfo){
            return super.querySuccess(pageInfo);
        }
        // 查询失败，返回失败code，系统消息
        else{
            return super.queryFailed();
        }
    }
    /**
     * @Author: jkm
     * @Description:
     *          根据主键删除用户
     * @Date: 19:47 2020/7/16
     * @param: [user]
     * @return com.aaa.pro.base.ResultData
     */
    @PostMapping("/deleteUser")
    public ResultData deleteUser(User user){
        Integer integer = iProjectService.deleteUser(user);
        if (integer > 0){
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }

    /**
     * @Author: jkm
     * @Description:
     *      根据id批量删除用户
     * @Date: 20:02 2020/7/16
     * @param: [ids]
     * @return com.aaa.pro.base.ResultData
     */
    @PostMapping("/deleteMoreUser")
    public ResultData deleteMoreUser(@RequestBody List<Object> ids){
        Integer integer = iProjectService.deletrMoreUser(ids);
        if (integer > 0){
            return super.deleteSuccess();
        }
    return super.deleteFailed();
    }
    /**
     * @Author: jkm
     * @Description:
     *      根据id查询用户
     * @Date: 20:08 2020/7/16
     * @param: [id]
     * @return com.aaa.pro.base.ResultData
     */
    @GetMapping("/selectUserById")
    public ResultData selectUserById(Long id){
        User user = iProjectService.selectUserById(id);
        if (!"".equals(user) && null != user){
            return super.querySuccess(user);
        }
        return super.queryFailed();
    }

    /**
     * @Author: jkm
     * @Description:
     *      根据id修改用户信息
     * @Date: 20:12 2020/7/16
     * @param: [user]
     * @return com.aaa.pro.base.ResultData
     */
    @PostMapping("/updateUserById")
    public ResultData updateUserById(User user){
        Integer integer = iProjectService.updateUserById(user);
        if (integer > 0){
            return super.updateSuccess();
        }
        return super.updateFailed();
    }
}
