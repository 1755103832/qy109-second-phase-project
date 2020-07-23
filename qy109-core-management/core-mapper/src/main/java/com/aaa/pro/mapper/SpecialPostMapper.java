package com.aaa.pro.mapper;

import com.aaa.pro.model.SpecialPost;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 单位审核--特殊岗位人员信息
 */
public interface SpecialPostMapper extends Mapper<SpecialPost> {

    /**
     * 查询特殊岗位人员信息
     */
    List<SpecialPost> selectSpecialPost(Long userId);

}