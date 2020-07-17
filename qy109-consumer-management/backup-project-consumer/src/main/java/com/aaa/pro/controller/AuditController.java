package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Audit;
import com.aaa.pro.service.IProjectService;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zyb
 * @Date Create in 2020/7/17 19:34
 * @Description
 **/
@RestController
@Api(tags = "项目审核接口")
public class AuditController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * @Author zyb
     * @Description 通过项目信息id编号查询项目审核记录
     * @Date 2020/7/17 19:37
     * @Param [id, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Audit>
     **/
    @GetMapping("/selectAuditRecordByMappingProjectId")
    @ApiOperation(value = "通过项目信息id编号查询项目审核记录")
    public ResultData selectAuditRecordByMappingProjectId(@RequestParam("id") Long id,
                                                          @RequestParam("pageNum") Integer pageNum,
                                                          @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Audit> auditPageInfo = projectService.selectAuditRecordByMappingProjectId(id, pageNum, pageSize);
        return StringUtils.isNotEmpty(String.valueOf(auditPageInfo)) ?
                super.querySuccess(auditPageInfo) : super.queryFailed();
    }

}
