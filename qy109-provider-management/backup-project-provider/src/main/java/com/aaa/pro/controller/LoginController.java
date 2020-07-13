package com.aaa.pro.controller;

import com.aaa.pro.model.User;
import com.aaa.pro.redis.RedisService;
import com.aaa.pro.service.LoginLogsService;
import com.aaa.pro.service.LoginService;
import com.aaa.pro.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.pro.staticproperties.RedisProperties.TRUE;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 17:11
 * @Description
 **/
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginLogsService loginLogsService;

    /**
     * @Author zyb
     * @Description 执行登录操作
     * @Date 2020/7/13 17:25
     * @Param [user]
     * @Return com.aaa.pro.vo.TokenVo
     **/
    @PostMapping("/doLogin")
    public TokenVo doLogin(@RequestBody User user) {
        TokenVo tokenVo = loginService.doLogin(user, redisService);
        try {
            if (TRUE.equals(tokenVo.getIfSuccess().toString())) {
                loginLogsService.doLoginLogs(user.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenVo;
    }
}
