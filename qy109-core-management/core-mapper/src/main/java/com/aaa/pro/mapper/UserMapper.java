package com.aaa.pro.mapper;

import com.aaa.pro.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @Author jkm
 * @Date Create in 2020/7/11 17:42
 * @Description
 **/
public interface UserMapper extends Mapper<User> {

    /**
     * 分页条件查询所有用户
     *
     * @param map
     * @return
     */
    List<HashMap> selectUserAll(HashMap map);
}