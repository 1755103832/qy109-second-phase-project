package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.Equipment;
import com.aaa.pro.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 19:17 2020/7/17
 * @description：
 */
@RestController
public class EquipmentController extends CommonController<Equipment> {

    @Autowired
    private EquipmentService equipmentService;

    @Override
    public BaseService getBaseService() {
        return equipmentService;
    }

    /**
     * @return java.util.List<com.aaa.pro.model.Equipment>
     * @Author: jkm
     * @Description: 根据 userId 查询设备信息
     * @Date: 19:30 2020/7/17
     * @param: [userId]
     */
    @GetMapping("/selectAllEquipmentByUserId")
    public List<Equipment> selectAllEquipmentByUserId(@RequestParam("userId") Long userId) {
        // 判断前端是否成功
        try {
            if (!"".equals(userId) && null != userId) {
                List<Equipment> equipment = equipmentService.selectAllEquipmentByUserId(userId);
                // 判断是否查询成功
                if (!"".equals(equipment) && null != equipment) {
                    // 返回查询结果
                    return equipment;
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
