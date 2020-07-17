package com.aaa.pro.controller;

import com.aaa.pro.annotation.LoginAnnotation;
import com.aaa.pro.base.BaseController;
import com.aaa.pro.model.User;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 17:36
 * @Description
 **/
@RestController
@Api(value = "用户登录", tags = "用户登录的接口")
public class LoginController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author zyb
     * @Description 执行登录操作
     * @Date 2020/7/13 17:41
     * @Param [user]
     * @Return com.aaa.pro.base.ResultData
     */
    @PostMapping("/doLogin")
    @ApiOperation(value = "执行登录操作", notes = "管理员登录")
    @LoginAnnotation(opeationType = "登录操作", opeationName = "管理员登录")
    public TokenVo doLogin(User user) {
        return iProjectService.doLogin(user);
    }

}
