package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.AuditMapper;
import com.aaa.pro.model.Audit;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 19:25
 * @Description
 **/
@Service
public class AuditService extends BaseService<Audit> {

    @Autowired
    private AuditMapper auditMapper;


    /**
     * @Author zyb
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 19:28
     * @Param [id, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Audit>
     **/
    public PageInfo<Audit> selectAuditRecordByMappingProjectId(Long id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (null != id && StringUtils.isNotEmpty(String.valueOf(id))) {
            List<Audit> audits = auditMapper.selectAuditRecordByMappingProjectId(id);
            if (StringUtils.isNotEmpty(String.valueOf(audits)) && audits.size() > 0) {
                return new PageInfo<>(audits);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
