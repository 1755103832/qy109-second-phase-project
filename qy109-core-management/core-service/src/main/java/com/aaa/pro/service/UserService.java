package com.aaa.pro.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.UserMapper;
import com.aaa.pro.model.User;
import com.aaa.pro.redis.RedisService;
import com.aaa.pro.utils.BaseUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.pro.staticproperties.CodeFormatProperties.*;
import static com.aaa.pro.status.CrudStatus.*;
import static com.aaa.pro.status.LoginStatus.LOGIN_TIMEOUT_EXIT;


/**
 * @Company: com.aaa
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.service
 * @Author： jkm
 * @Date： Create in 10:30 2020/7/16
 * @description： 用户的增删改查
 */
@Service
public class UserService extends BaseService<User> {
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 新增用户
     * @Author: jkm
     * @Date: 2020/5/20 15:42
     * @Param: [user]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> addUser(User user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //可以查到redis里的token，然后可以进行新增用户
        user.setCreateTime(DateUtil.now());
        int addResult = userMapper.insert(user);
        if (addResult > 0) {
            resultMap.put(CODE, INSERT_SUCCESS.getCode());
            resultMap.put(MSG, INSERT_SUCCESS.getMessage());
        } else {
            resultMap.put(CODE, INSERT_FAILED.getCode());
            resultMap.put(MSG, INSERT_FAILED.getMessage());
        }
        return resultMap;
    }


    /**
     * @Description: 批量删除用户
     * @Author: jkm
     * @Date: 2020/5/20 20:50
     * @Param: [ids]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> delUser(List<Long> ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //获取到参数类型，然后添加一个where条件，是in类型，id属于ids中的
        Example example = Example.builder(User.class).where(Sqls.custom().andIn("id", ids)).build();
        int i = userMapper.deleteByExample(example);
        if (i > 0) {
            resultMap.put(CODE, DELETE_SUCCESS.getCode());
            resultMap.put(MSG, DELETE_SUCCESS.getMessage());
        } else {
            resultMap.put(CODE, DELETE_FAILED.getCode());
            resultMap.put(MSG, DELETE_FAILED.getMessage());
        }
        return resultMap;
    }


    /**
     * @Description: 修改员工信息
     * @Author: jkm
     * @Date: 2020/5/21 16:00
     * @Param: [user]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> updateUser(User user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        user.setModifyTime(DateUtil.now());
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            resultMap.put(CODE, UPDATE_SUCCESS.getCode());
            resultMap.put(MSG, UPDATE_SUCCESS.getMessage());
        } else {
            resultMap.put(CODE, UPDATE_FAILED.getCode());
            resultMap.put(MSG, UPDATE_FAILED.getMessage());
        }
        return resultMap;
    }


    /**
     * @Description: 查询全部用户信息
     * @Author: jkm
     * @Date: 2020/5/21 19:29
     * @Param: []
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<User> users = userMapper.selectAll();
        if (null != users && !users.isEmpty()) {
            resultMap.put(CODE, QUERY_SUCCESS.getCode());
            resultMap.put(MSG, QUERY_SUCCESS.getMessage());
            resultMap.put(DATA, users);
            return resultMap;
        } else {
            resultMap.put(CODE, QUERY_FAILED.getCode());
            resultMap.put(MSG, QUERY_FAILED.getMessage());
        }
        return resultMap;
    }

    /**
     * @return com.github.pagehelper.PageInfo<java.util.HashMap>
     * @throws
     * @Author jkm
     * @Description 多表分页查询所有用户
     * @Param [map]
     * @Data 2020/5/22
     */
    public PageInfo<HashMap> selectUserPageInfo(HashMap map) {
        PageHelper.startPage(BaseUtils.trans2Int(map.get("pageNo")), BaseUtils.trans2Int(map.get("pageSize")));
        List<HashMap> list = userMapper.selectUserAll(map);
        PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(list);
        if (null != pageInfo && !"".equals(pageInfo)) {
            return pageInfo;
        }
        return null;
    }

    /**
     * @return com.github.pagehelper.PageInfo<T>
     * @throws
     * @Author jkm
     * @Description 分页查询全部用户
     * @Param [t, pageNo, pageSize]
     * @Data 2020/5/20
     */
    public Map<String, Object> selectUserAll(HashMap map, RedisService redisService) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String tokenVal = redisService.getString(map.get("tokenId").toString());
        //检测token
        if (null == tokenVal) {
            resultMap.put(CODE, LOGIN_TIMEOUT_EXIT.getCode());
            resultMap.put(MSG, LOGIN_TIMEOUT_EXIT.getMessage());
            return resultMap;
        }
        if (map.size() > 0) {
            //调用BaseService分页条件查询
            PageInfo<HashMap> pageInfo = selectUserPageInfo(map);
            if (null != pageInfo && pageInfo.getSize() > 0) {
                resultMap.put(CODE, QUERY_SUCCESS.getCode());
                resultMap.put(MSG, pageInfo);
                return resultMap;
            } else {
                resultMap.put(CODE, QUERY_FAILED.getCode());
                resultMap.put(MSG, QUERY_FAILED.getMessage());
            }
        }
        return resultMap;
    }

}