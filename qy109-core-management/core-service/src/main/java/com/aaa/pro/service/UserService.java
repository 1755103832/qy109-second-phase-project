package com.aaa.pro.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.DeptMapper;
import com.aaa.pro.mapper.UserMapper;
import com.aaa.pro.model.Dept;
import com.aaa.pro.model.User;
import com.aaa.pro.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.pro.staticproperties.CodeFormatProperties.*;
import static com.aaa.pro.status.CrudStatus.*;


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

    @Autowired
    private DeptMapper deptMapper;

    /**
     * @Author: JKM
     * @Date: 2020/7/20 15:42
     * @Param: [user]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 这里有一个判断 如果这里已经有Id 说明数据存在 这里就会执行更新操作
     * 如果Id不存在 说明此时是添加操作
     */
    public Map<String, Object> addUser(User user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 判断user对象是否拿到
        if ("".equals(user) || user == null) {
            resultMap.put(CODE, INSERT_DATA_FAILED.getCode());
            resultMap.put(MSG, INSERT_DATA_FAILED.getMessage());
        } else {
            user.setCreateTime(DateUtil.now());
            if ("".equals(user.getId()) || user.getId() == null) {
                int INSERTResult = userMapper.insert(user);

                if (INSERTResult > 0) {
                    resultMap.put(CODE, INSERT_DATA_SUCCESS.getCode());
                    resultMap.put(MSG, INSERT_DATA_SUCCESS.getMessage());
                } else {
                    resultMap.put(CODE, INSERT_DATA_FAILED.getCode());
                    resultMap.put(MSG, INSERT_DATA_FAILED.getMessage());
                }
            } else {
                user.setModifyTime(DateUtil.now());
                int i = userMapper.updateByPrimaryKeySelective(user);
                if (i > 0) {
                    resultMap.put(CODE, UPDATE_DATA_SUCCESS.getCode());
                    resultMap.put(MSG, UPDATE_DATA_SUCCESS.getMessage());
                } else {
                    resultMap.put(CODE, UPDATE_DATA_FAILED.getCode());
                    resultMap.put(MSG, UPDATE_DATA_FAILED.getMessage());
                }
            }
        }
        return resultMap;
    }


    /**
     * @Description: 批量删除用户
     * @Author: jkm
     * @Date: 2020/7/20 20:50
     * @Param: [ids]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> delUser(List<Long> ids) {
        // TODO 批量删除 可能会出现一种情况 传过来5个用户id 只删除了三个 而且这里应该是逻辑删除

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //获取到参数类型，然后添加一个where条件，是int类型，id属于ids中的
        try {
            Example example = Example.builder(User.class).where(Sqls.custom().andIn("id", ids)).build();
            int i = userMapper.deleteByExample(example);
            if (i > 0) {
                resultMap.put(CODE, DELETE_DATA_SUCCESS.getCode());
                resultMap.put(MSG, DELETE_DATA_SUCCESS.getMessage());
            } else {
                resultMap.put(CODE, DELETE_DATA_FAILED.getCode());
                resultMap.put(MSG, DELETE_DATA_FAILED.getMessage());
            }
        } catch (Exception exception) {
            System.out.println(EXP);
        }
        return resultMap;
    }

    /**
     * @Author: jkm
     * @Time: 23:11 2020/7/23
     * @Params: [id]
     * @Return: java.lang.Boolean
     * @Throws:
     * @Description: 通过Id删除用户信息
     */

    public Boolean deleteUserByUserId(Long UserId) {
        if (UserId != null) {
            int i = userMapper.deleteByPrimaryKey(UserId);
            return i > 0;
        }
        return false;

    }

    /**
     * @Description: 修改员工信息
     * @Author: jkm
     * @Date: 2020/7/21 16:00
     * @Param: [user]
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> updateUser(User user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        user.setModifyTime(DateUtil.now());
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            resultMap.put(CODE, UPDATE_DATA_SUCCESS.getCode());
            resultMap.put(MSG, UPDATE_DATA_SUCCESS.getMessage());
        } else {
            resultMap.put(CODE, UPDATE_DATA_FAILED.getCode());
            resultMap.put(MSG, UPDATE_DATA_FAILED.getMessage());
        }
        return resultMap;
    }


    /**
     * @Description: 查询全部用户信息
     * @Author: jkm
     * @Date: 2020/7/20 19:29
     * @Param: []
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<User> users = userMapper.selectAll();

        List<UserVo> userVos = new ArrayList<>();
        if (users.size() > 0) {
            for (User user1 : users) {
                UserVo userVo = new UserVo();
                // 通过user对象拿到deptId
                Long deptId = user1.getDeptId();
                // 判断deptId是否存在
                if ("".equals(deptId) || deptId == null) {
                    // 如果deptId不存在 说明该用户并未分配部门
                    // 此时直接把user中的对象给userVo
                    userVo.setEmail(user1.getEmail())
                            .setDeptName(PD)
                            .setMobile(user1.getMobile())
                            .setSsex(user1.getSsex())
                            .setUsername(user1.getUsername())
                            .setStatus(user1.getStatus())
                            .setToken(user1.getToken())
                            .setId(user1.getId())
                            .setCreateTime(user1.getCreateTime());
                    userVos.add(userVo);


                } else {
                    // 如果deptId存在 通过deptId拿到dept的数据
                    Dept dept = deptMapper.selectByPrimaryKey(deptId);
                    // 此处判断dept是否真的拿到 也就是说判断dept数据是否是空
                    if ("".equals(dept) || dept == null) {
                        // 如果么有拿到数据 也将user中的对象赋值给userVo
                        userVo.setDeptName("")
                                .setEmail(user1.getEmail())
                                .setMobile(user1.getMobile())
                                .setSsex(user1.getSsex())
                                .setUsername(user1.getUsername())
                                .setStatus(user1.getStatus())
                                .setToken(user1.getToken())
                                .setId(user1.getId())
                                .setCreateTime(user1.getCreateTime());
                        userVos.add(userVo);
                    } else {
                        // 此处判断user的deptId是否和dept中的id 是否相等
                        if (dept.getDeptId().equals(user1.getDeptId())) {
                            // 如果相等 就把deptName赋值给userVO
                            userVo.setDeptName(dept.getDeptName())
                                    .setEmail(user1.getEmail())
                                    .setMobile(user1.getMobile())
                                    .setSsex(user1.getSsex())
                                    .setUsername(user1.getUsername())
                                    .setStatus(user1.getStatus())
                                    .setAvatar(user1.getAvatar())
                                    .setType(user1.getType())
                                    .setToken(user1.getToken())
                                    .setId(user1.getId())
                                    .setCreateTime(user1.getCreateTime());
                            userVos.add(userVo);

                        } else {
                            userVo
                                    .setEmail(user1.getEmail())
                                    .setMobile(user1.getMobile())
                                    .setSsex(user1.getSsex())
                                    .setUsername(user1.getUsername())
                                    .setStatus(user1.getStatus())
                                    .setToken(user1.getToken())
                                    .setId(user1.getId())
                                    .setCreateTime(user1.getCreateTime());
                            userVos.add(userVo);
                        }
                    }

                }


            }
            resultMap.put(CODE, QUERY_DATA_SUCCESS.getCode());
            resultMap.put(MSG, QUERY_DATA_SUCCESS.getMessage());
            resultMap.put(DATA, userVos);
            return resultMap;
        } else {
            resultMap.put(CODE, QUERY_DATA_FAILED.getCode());
            resultMap.put(MSG, QUERY_DATA_FAILED.getMessage());
        }
        return resultMap;
    }

    /**
     * @return com.github.pagehelper.PageInfo<T>
     * @throws
     * @Author jkm
     * @Description 分页查询全部用户
     * @Param [t, pageNo, pageSize]
     * @Data 2020/7/20
     */
    public Map<String, Object> selectUserAll(String userName, DeptService deptService) {
        User user = new User();
        user.setUsername(userName);

        List<UserVo> userVos = new ArrayList<>();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //String tokenVal = redisService.get(map.get("tokenId").toString());
        //检测token
       /* if (null == tokenVal){
            resultMap.put(CODE,LOGIN_TIMEOUT_EXIT.getCode());
            resultMap.put(MSG,LOGIN_TIMEOUT_EXIT.getMessage());
            return resultMap;
        }*/
        if (!userName.isEmpty()) {
            //调用BaseService分页条件查询
            List<User> users = userMapper.select(user);

            // 判断users是否有数据
            if (users.size() > 0) {

                // 如果users有数据
                // 遍历循环拿到users中的每一个对象
                for (User user1 : users) {
                    UserVo userVo = new UserVo();
                    // 通过user对象拿到deptId
                    Long deptId = user1.getDeptId();
                    // 判断deptId是否存在
                    if ("".equals(deptId) || deptId == null) {
                        // 如果deptId不存在 说明该用户并未分配部门
                        // 此时直接把user中的对象给userVo
                        userVo.setEmail(user1.getEmail())
                                .setDeptName(PD)
                                .setMobile(user1.getMobile())
                                .setSsex(user1.getSsex())
                                .setUsername(user1.getUsername())
                                .setStatus(user1.getStatus())
                                .setToken(user1.getToken())
                                .setId(user1.getId())
                                .setCreateTime(user1.getCreateTime());
                        userVos.add(userVo);


                    } else {
                        // 如果deptId存在 通过deptId拿到dept的数据
                        Dept dept = deptMapper.selectByPrimaryKey(deptId);
                        // 此处判断dept是否真的拿到 也就是说判断dept数据是否是空
                        if ("".equals(dept) || dept == null) {
                            // 如果么有拿到数据 也将user中的对象赋值给userVo
                            userVo.setDeptName("")
                                    .setEmail(user1.getEmail())
                                    .setMobile(user1.getMobile())
                                    .setSsex(user1.getSsex())
                                    .setUsername(user1.getUsername())
                                    .setStatus(user1.getStatus())
                                    .setToken(user1.getToken())
                                    .setId(user1.getId())
                                    .setCreateTime(user1.getCreateTime());
                            userVos.add(userVo);
                        } else {
                            // 此处判断user的deptId是否和dept中的id 是否相等
                            if (dept.getDeptId().equals(user1.getDeptId())) {
                                // 如果相等 就把deptName赋值给userVO
                                userVo.setDeptName(dept.getDeptName())
                                        .setEmail(user1.getEmail())
                                        .setMobile(user1.getMobile())
                                        .setSsex(user1.getSsex())
                                        .setUsername(user1.getUsername())
                                        .setStatus(user1.getStatus())
                                        .setAvatar(user1.getAvatar())
                                        .setType(user1.getType())
                                        .setToken(user1.getToken())
                                        .setId(user1.getId())
                                        .setCreateTime(user1.getCreateTime());
                                userVos.add(userVo);

                            } else {
                                userVo
                                        .setEmail(user1.getEmail())
                                        .setMobile(user1.getMobile())
                                        .setSsex(user1.getSsex())
                                        .setUsername(user1.getUsername())
                                        .setStatus(user1.getStatus())
                                        .setToken(user1.getToken())
                                        .setId(user1.getId())
                                        .setCreateTime(user1.getCreateTime());
                                userVos.add(userVo);
                            }
                        }

                    }


                }


                resultMap.put(CODE, QUERY_DATA_SUCCESS.getCode());
                resultMap.put(MSG, QUERY_DATA_SUCCESS.getMessage());
                resultMap.put(DATA, userVos);

            } else {
                resultMap.put(CODE, QUERY_DATA_FAILED.getCode());
                resultMap.put(MSG, QUERY_DATA_FAILED.getMessage());
            }
        }
        return resultMap;
    }
}