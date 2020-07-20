package com.aaa.pro.mapper;

import com.aaa.pro.model.Technicist;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TechnicistMapper extends Mapper<Technicist> {

    /**
     * 查询技术人员信息
     * @param id
     * @return
     */
     List<Technicist> queryTechnicist(Long id);

    /**
     * 修改技术人员信息
     * @param technicist
     * @return
     */
    int updataTechnicist(Technicist technicist);
}