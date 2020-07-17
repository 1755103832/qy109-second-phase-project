package com.aaa.pro.mapper;

import com.aaa.pro.model.Mapping_unit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseInfomationMapper extends Mapper<Mapping_unit> {

    List<Mapping_unit> queryMapping_unit(Long userId);


}