package com.aaa.pro.mapper;

import com.aaa.pro.model.Audit;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 19:17
 * @Description
 **/
public interface AuditMapper extends Mapper<Audit> {

    /**
     * @Author zyb
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 19:24
     * @Param [id]
     * @Return java.util.List<com.aaa.pro.model.Audit>
     **/
    List<Audit> selectAuditRecordByMappingProjectId(Long id);

}