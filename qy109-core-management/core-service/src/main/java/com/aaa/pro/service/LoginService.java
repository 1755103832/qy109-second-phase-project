package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.UserMapper;
import com.aaa.pro.model.User;
import com.aaa.pro.redis.RedisService;
import com.aaa.pro.utils.UUIDUtils;
import com.aaa.pro.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.aaa.pro.staticproperties.RedisProperties.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 10:32
 * @Description
 **/
@Service
public class LoginService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Author zyb
     * @Description 执行登录操作
     * @Date 2020/7/13 10:34
     * @Param [user, redisService]
     * @Return com.aaa.pro.vo.TokenVo
     **/
    public TokenVo doLogin(User user, RedisService redisService) {
        TokenVo tokenVo = new TokenVo();
        tokenVo.setIfSuccess(false);
        // 判断user是否为空(用户在执行登录操作的时候-->肯定没有token)
        if (null != user) {
            // 验证用户名和密码是否正确
            User one = userMapper.selectOne(user);
            // 判断从数据库中查询到的user对象是否为空
            if (null != one) {
                // 如果查询到的user对象不为null，就随机一个uuid
                // 说明用户输入的账户和密码都正确，登录成功
                String token = UUIDUtils.getUUID();
                // 把生成出来的uuid(即token)set进去
                one.setToken(token);
                // 然后再更新一下数据库中的数据，添加token
                int updateResult = userMapper.updateByPrimaryKey(one);
                // 判断token是否更新成功
                if (updateResult > 0) {
                    // 说明token更新成功，需要返回token
                    // 这个时候为了提高效率，把token值存入redis缓存，避免每次查询token的时候都要去查询数据库
                    // 需要给token设置一个失效时间(1800s-->3min)
                    String set = redisService.set(String.valueOf(one.getId()), token, XX, EX, 1800);
                    System.out.println(set);
                    if (OK.equals(set.toUpperCase()) || ONE.equals(set)) {
                        return tokenVo.setIfSuccess(true).setToken(token).setRedisKey(String.valueOf(one.getId()));
                    }
                }
            }
        }
        return tokenVo;
    }
}
