package com.aaa.pro.mapper;

import com.aaa.pro.model.Equipment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: jkm
 * @Description:
 */
public interface EquipmentMapper extends Mapper<Equipment> {

    /**
     * @return java.util.List<com.aaa.pro.model.Equipment>
     * @Author: jkm
     * @Description: 根据userId查询设备信息
     * @Date: 17:46 2020/7/17
     * @param: [userId]
     */
    List<Equipment> selectEquipmentByUserId(Long userId);
}