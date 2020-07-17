package com.aaa.pro.mapper;

import com.aaa.pro.model.User;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;

/**
 * @Author zyb
 * @Date Create in 2020/7/11 17:42
 * @Description
 **/
public interface UserMapper extends Mapper<User> {

    /**
     *  通过username查询用户
     * @param username
     * @return
     */
    HashMap<String, Object> selectId(@RequestParam("username") String username);
}