package com.aaa.pro.controller;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.model.Role;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company: com.aaa.jkm
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 10:41 2020/7/16
 * @description：
 */
@RestController
public class RoleController extends CommonController<Role> {


    @Override
    public BaseService<Role> getBaseService() {
        return null;
    }
}
