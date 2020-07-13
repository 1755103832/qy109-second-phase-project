package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
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
@Api(value = "登录信息", tags = "用户登录接口")
public class LoginController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author zyb
     * @Description 执行登录操作
     * @Date 2020/7/13 17:41
     * @Param [user]
     * @Return com.aaa.pro.base.ResultData
     **/
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录功能", notes = "用户执行登录功能")
    public ResultData doLogin(User user) {
        TokenVo tokenVo = iProjectService.doLogin(user);
        if (tokenVo.getIfSuccess()) {
            return super.loginSuccess(tokenVo.getToken());
        }
        return super.loginFailed();
    }
}
