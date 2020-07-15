package com.aaa.pro.service;

import com.aaa.pro.model.LoginLogs;
import com.aaa.pro.model.User;
import com.aaa.pro.vo.TokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 17:31
 * @Description
 **/
@FeignClient(value = "")
public interface IProjectService {

    /**
     * @Author zyb
     * @Description 执行登录操作
     * @Date 2020/7/13 17:33
     * @Param [user]
     * @Return com.aaa.pro.vo.TokenVo
     **/
    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);
  /**
   * @description: TODO
   *   新增日志
   * @params:   loginLogs
   * @return: java.lang.Integer
   * @author: Wen
   * @date: 2020/7/15 16:13
   */
    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLogs loginLogs);


}
