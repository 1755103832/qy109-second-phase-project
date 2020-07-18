package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.Audit;
import com.aaa.pro.service.AuditService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 19:31
 * @Description
 **/
@RestController
public class AuditController extends CommonController<Audit> {

    @Autowired
    private AuditService auditService;

    /**
     * getBaseService
     *
     * @return
     */
    @Override
    public BaseService<Audit> getBaseService() {
        return auditService;
    }

    /**
     * @Author zyb
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 19:28
     * @Param [id, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Audit>
     **/
    @GetMapping("/selectAuditRecordByMappingProjectId")
    public PageInfo<Audit> selectAuditRecordByMappingProjectId(@RequestParam("id") Long id,
                                                               @RequestParam("pageNum") Integer pageNum,
                                                               @RequestParam("pageSize") Integer pageSize) {
        return auditService.selectAuditRecordByMappingProjectId(id, pageNum, pageSize);
    }


    /**
     * @Author zyb
     * @Description 根据关联业务编号查询审核记录
     * @Date 2020/7/18 9:13
     * @Param [refId, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Audit>
     **/
    @GetMapping("/selectAuditByRefId")
    public PageInfo<Audit> selectAuditByRefId(@RequestParam("refId") Long refId,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        return auditService.selectAuditByRefId(refId, pageNum, pageSize);
    }

    /**
     * @Author zyb
     * @Description 项目审核(汇交成果信息 - - > 查看审核记录 ( 按钮))
     * @Date 2020/7/18 9:31
     * @Param [id, pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.Audit>
     **/
    @GetMapping("/selectAuditByMappingProjectId")
    public PageInfo<Audit> selectAuditByMappingProjectId(@RequestParam("id") Long id,
                                                         @RequestParam("pageNum") Integer pageNum,
                                                         @RequestParam("pageSize") Integer pageSize) {
        return auditService.selectAuditByMappingProjectId(id, pageNum, pageSize);
    }
}
