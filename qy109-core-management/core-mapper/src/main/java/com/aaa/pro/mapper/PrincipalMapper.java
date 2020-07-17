package com.aaa.pro.mapper;

import com.aaa.pro.model.Principal;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 * @description:
 *    测绘管理--单位基本信息--单位负责人
 * @params:
 * @return:
 * @author: Wen
 * @date: 2020/7/17 22:29
 */
public interface PrincipalMapper extends Mapper<Principal> {

   //查询负责人信息
    List<Principal> queryOne(Long id);
    //修改负责人信息
    int updateList(Principal principal);

}