package com.aaa.pro.controller;

import com.aaa.pro.annotation.LoginAnnotation;
import com.aaa.pro.base.BaseController;
import com.aaa.pro.model.User;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 17:36
 * @Description
 **/
@RestController
public class LoginController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author zyb
     * @Description 执行登录操作
     * @Date 2020/7/13 17:41
     * @Param [user]
     * @Return com.aaa.pro.base.ResultData
     *
     * @return*/
    @PostMapping("/doLogin")
    @LoginAnnotation(opeationType = "登录操作", opeationName = "管理员登录")
    public TokenVo doLogin(User user) {
        return iProjectService.doLogin(user);
    }

}
