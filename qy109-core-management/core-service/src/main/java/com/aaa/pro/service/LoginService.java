package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.model.User;
import com.aaa.pro.vo.TokenVo;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * @Author zyb
 * @Date Create in 2020/7/13 10:32
 * @Description
 **/
@Service
public class LoginService extends BaseService<User> {


   /**
    *
    * @description:
    *        执行登录操作
    * @params:  * @param user
    * @return: com.aaa.pro.vo.TokenVo
    * @author: Wen
    * @date: 2020/7/15 15:25
    */
    public TokenVo doLogin(User user) {
        TokenVo tokenVo = new TokenVo();
        User user1 = new User();
        // 1.判断User是否为null
        if(null != user) {
            user1.setUsername(user.getUsername());
            User user2 = super.selectOne(user1);
            // 2.判断user2是否为null
            if(null == user2) {
                tokenVo.setIfSuccess(false).setType(1);
                return tokenVo;
            } else {
                // 用户名OK，查询密码
                user1.setPassword(user.getPassword());
                User user3 = super.selectOne(user1);
                // 3.判断user3是否为null
                if(null == user3) {
                    tokenVo.setIfSuccess(false).setType(2);
                    return tokenVo;
                } else {
                    // 登录成功
                    /**
                     * !!!!!!mybatis是无法检测连接符的，他会把连接符进行转译(\\-)
                     * 需要把连接符替换掉
                     */
                    String token = UUID.randomUUID().toString().replaceAll("-","");
                    user3.setToken(token);
                    Integer updateResult = super.updateByPrimaryKeySelective(user3);
                    if(updateResult > 0) {
                        tokenVo.setIfSuccess(true).setToken(token);
                    } else {
                        tokenVo.setIfSuccess(false).setType(4);
                        return tokenVo;
                    }
                }
            }
        } else {
            tokenVo.setIfSuccess(false).setType(4);
            return tokenVo;
        }
        return tokenVo;
    }

}