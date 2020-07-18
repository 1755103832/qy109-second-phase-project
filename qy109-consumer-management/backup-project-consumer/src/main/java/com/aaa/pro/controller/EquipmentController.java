package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.Equipment;
import com.aaa.pro.service.IProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 19:31 2020/7/17
 * @description：
 */
@RestController
@Api(value = "设备信息", tags = "设备信息接口")
public class EquipmentController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @return com.aaa.pro.base.ResultData
     * @Author: jkm
     * @Description: 根据userId 查询设备信息
     * @Date: 20:03 2020/7/17
     * @param: [userId]
     */
    @GetMapping("/selectAllEquipmentByUserId")
    public ResultData selectAllEquipmentByUserId(@RequestParam("userId") Long userId) {
        List<Equipment> equipment = iProjectService.selectAllEquipmentByUserId(userId);
        if (!"".equals(equipment) && null != equipment) {
            return super.querySuccess(equipment);
        }
        return super.queryFailed();
    }
}
