package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.BaseInfomationMapper;
import com.aaa.pro.model.Mapping_unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单位审核模块
 */
@Service
public class BaseInfomationService extends BaseService<Mapping_unit> {
    @Autowired
    private BaseInfomationMapper baseInfomationMapper;


   /**
    * @description:
    *   查询单位基本信息
    * @params: [userId]
    * @return: java.util.List<com.aaa.pro.model.Mapping_unit>
    * @author: Wen
    * @date: 2020/7/17 16:22
    */
    public List<Mapping_unit> queryMapping_unit(Long userId){
        List<Mapping_unit> mapping_units = baseInfomationMapper.queryMapping_unit(userId);
        if (null!=mapping_units){
            return mapping_units;
        }
        return null;
    }
}
