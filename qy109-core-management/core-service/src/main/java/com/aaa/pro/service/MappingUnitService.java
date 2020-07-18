package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.MappingUnitMapper;
import com.aaa.pro.model.MappingUnit;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/18 18:52
 * @Description
 **/
@Service
public class MappingUnitService extends BaseService<MappingUnit> {

    @Autowired
    private MappingUnitMapper mappingUnitMapper;

    /**
     * @Author zyb
     * @Description 查询白名单人员信息
     * @Date 2020/7/18 18:55
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    public PageInfo<MappingUnit> selectByUnitStatus(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingUnit> units = mappingUnitMapper.selectByUnitStatus();
        return StringUtils.isNotEmpty(units.toString()) && units.size() > 0 ? new PageInfo<>(units) : null;
    }

    /**
     * @Author zyb
     * @Description 查询黑名单人员信息
     * @Date 2020/7/18 19:05
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    public PageInfo<MappingUnit> selectByUnitStatus2(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingUnit> units = mappingUnitMapper.selectByUnitStatus2();
        return StringUtils.isNotEmpty(units.toString()) ? new PageInfo<>(units) : null;
    }
}
