package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.User;
import com.aaa.pro.service.LoginService;
import com.aaa.pro.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static com.aaa.pro.status.LoginStatus.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 17:11
 * @Description
 **/
@RestController
public class LoginController extends CommonController<User> {

    @Autowired
    private LoginService loginService;

    @Override
    public BaseService<User> getBaseService() {
        return loginService;
    }

    /**
     * @description: 执行登录操作
     * @params: * @param user
     * @return: ResultData
     * @author: Wen
     * @date: 2020/7/15 15:48
     */
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user) {
        TokenVo tokenVo = loginService.doLogin(user);
        if (tokenVo.getIfSuccess()) {
            return super.loginSuccess(tokenVo.getToken());
        } else if (tokenVo.getType() == 1) {
            return super.loginFailed(USER_NOT_EXIST.getMessage());
        } else if (tokenVo.getType() == 2) {
            return super.loginFailed(PASSWORD_WRONG.getMessage());
        } else {
            return super.loginFailed(SYSTEM_EXCEPTION.getMessage());
        }
    }
}


