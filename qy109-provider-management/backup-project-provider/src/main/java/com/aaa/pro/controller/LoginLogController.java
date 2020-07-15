package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.LoginLogs;
import com.aaa.pro.service.LoginLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginLogController extends CommonController<LoginLogs> {

    @Autowired
    private LoginLogsService loginLogsService;

    @Override
    public BaseService<LoginLogs> getBaseService() {
        return loginLogsService;
    }


    /**
     *
     * @description:
     *     保存日志
     * @params:   loginLogs
     * @return: java.lang.Integer
     * @author: Wen
     * @date: 2020/7/15 16:03
     */
    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLogs loginLogs ) {
        return getBaseService().insert(loginLogs);
    }

}
