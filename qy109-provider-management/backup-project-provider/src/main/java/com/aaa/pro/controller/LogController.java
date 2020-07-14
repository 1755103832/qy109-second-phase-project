package com.aaa.pro.controller;


import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.LoginLogs;
import com.aaa.pro.service.LoginLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LogController extends CommonController<LoginLogs> {
    @Autowired
    private LoginLogsService loginLogsService;

    @Override
    public BaseService<LoginLogs> getBaseService() {
        return loginLogsService;
    }

   /**
    *
    * @description:
    *    保存登陆日志
    * @return: com.aaa.pro.base.ResultData
    * @author: Wen
    * @date: 2020/7/14 16:29
    */
    @PostMapping("/addLoginLog")
    public ResultData addLoginLog(@RequestBody Map map) {
        return super.add(map);
    }


}
