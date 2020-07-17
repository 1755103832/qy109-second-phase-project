package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.EquipmentMapper;
import com.aaa.pro.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.service
 * @Author： jkm  测绘-单位-设备信息管理
 * @Date： Create in 18:31 2020/7/17
 * @description：
 */
@Service
public class EquipmentService extends BaseService<Equipment> {
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * @return java.util.List<com.aaa.pro.model.Equipment>
     * @Author: jkm
     * @Description: 根据userId 查询设备信息
     * @Date: 18:37 2020/7/17
     * @param: [userId]
     */
    public List<Equipment> selectAllEquipmentByUserId(Long userId) {

        try {
            // 判断前端是否传值成功
            if (!"".equals(userId) && null != userId) {
                List<Equipment> equipmentList = equipmentMapper.selectEquipmentByUserId(userId);
                // 判断查询结果是否为空
                if (!"".equals(equipmentList) && null != equipmentList) {
                    //返回查询结果
                    return equipmentList;
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
