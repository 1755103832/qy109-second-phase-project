package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.UserMapper;
import com.aaa.pro.model.User;
import com.aaa.pro.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.aaa.pro.staticproperties.DateTimeFormatProperties.TIME_FORMAT;


/**
 * @Company: com.aaa.jkm
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
     * @return com.github.pagehelper.PageInfo<com.aaa.pro.model.User>
     * @Author: jkm
     * @Description: 查询所有用户
     * @Date: 10:51 2020/7/16
     * @param: [pageNo, pageSize]
     */
    public PageInfo<User> selectAllUser(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> users = userMapper.selectAll();
        if (users.size() > 0) {
            PageInfo<User> pageInfo = new PageInfo<>(users);
            return pageInfo;
        } else {
            return null;
        }
    }

    /**
     * @return com.aaa.pro.model.User
     * @Author: jkm
     * @Description: 根据id查询用户
     * @Date: 15:47 2020/7/16
     * @param: [id]
     */
    public User selectUserById(Long id) {
        // 判断前端是否传值成功
        if (!"".equals(id) && null != id) {
            try {
                User user = userMapper.selectByPrimaryKey(id);
                // 判断是否查询出数据
                if (!"".equals(user) && null != id) {
                    return user;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @return java.lang.Boolean
     * @Author: jkm
     * @Description: 添加用户，这个参数user是从前端传过来的
     * @Date: 11:06 2020/7/16
     * @param: [user]
     */
    public Boolean addUser(User user) {

        try {
            // 获取当前时间
            Date date = new Date();
            // 设置日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
            String format = simpleDateFormat.format(date);
            // 获取一个token值
            String token = UUIDUtils.getUUID();
            // 获取前端传入的参数，放入user中
            // 将userName传入
            user.setUsername(user.getUsername())
                    .setToken(token)
                    // 传入password
                    .setPassword(user.getPassword())
                    // 传入Email
                    .setEmail(user.getEmail())
                    // 传入手机号
                    .setMobile(user.getMobile())
                    // 传入角色
                    .setType(user.getType())
                    // 传入部门
                    .setDeptId(user.getDeptId())
                    // 传入状态
                    .setStatus(user.getStatus())
                    // 传入性别
                    .setSsex(user.getSsex())
                    // 传入创建时间
                    .setCreateTime(format)
                    // 传入password
                    .setId(user.getId());
            int insert = userMapper.insert(user);
            if (insert > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return java.lang.Integer
     * @Author: jkm
     * @Description: 通过主键删除用户
     * @Date: 11:46 2020/7/16
     * @param: [user]
     */
    public Integer deleteUser(User user) {
        // 判断前端是否传值成功
        if (!"".equals(user) && user != null) {
            try {
                // 执行删除操作
                int delete = userMapper.delete(user);
                if (delete > 0) {
                    return delete;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @return java.lang.Integer
     * @Author: jkm
     * @Description: 根据id批量删除用户
     * @Date: 15:34 2020/7/16
     * @param: [ids]
     */
    public Integer deleteMoreUser(List<Object> ids) {
        // 判断前端是否传值成功
        if (!"".equals(ids) && null != ids) {
            try {
                //调用父类的批量删除方法
                Integer integer = super.batchDeleteByIds(ids);
                if (integer > 0) {
                    return integer;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @return java.lang.Integer
     * @Author: jkm
     * @Description: 根据id修改用户信息
     * @Date: 15:56 2020/7/16
     * @param: [user]
     */
    public Integer updateUserById(User user) {
        // 判断前端是否传值成功
        if (!"".equals(user) && null != user) {
            // 获取当前时间作为修改时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
            String format = simpleDateFormat.format(date);
            // 把时间存到实体中
            user.setModifyTime(format);
            try {
                // 通过父类方法修改用户信息
                Integer update = super.updateByPrimaryKeySelective(user);
                // 判断受影响的行数
                if (update > 0) {
                    return update;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}