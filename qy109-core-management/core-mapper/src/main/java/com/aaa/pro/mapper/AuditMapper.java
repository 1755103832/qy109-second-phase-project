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

    /**
     * @Author zyb
     * @Description 根据关联业务编号查询审核记录
     * @Date 2020/7/18 9:06
     * @Param [refId]
     * @Return java.util.List<com.aaa.pro.model.Audit>
     **/
    List<Audit> selectAuditByRefId(Long refId);

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息 - - > 查看审核记录 ( 按钮))
     * @Date 2020/7/18 9:29
     * @Param [id]
     * @Return java.util.List<com.aaa.pro.model.Audit>
     **/
    List<Audit> selectAuditByMappingProjectId(Long id);
}