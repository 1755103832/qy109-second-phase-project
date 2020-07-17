package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.PrincipalMapper;
import com.aaa.pro.model.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @description:
 *   测绘管理-单位基本信息-负责人信息
 * @params:
 * @return:
 * @author: Wen
 * @date: 2020/7/17 22:42
 */
@Service
public class PrincipalService extends BaseService<Principal> {

    @Autowired
    private PrincipalMapper principalMapper;

    /**
     * @description:
     *   查询负责人信息
     * @params: [userId]
     * @return: java.util.List<com.aaa.pro.model.Principal>
     * @author: Wen
     * @date: 2020/7/17 22:42
     */
    public List<Principal> queryOne(Long userId){
        List<Principal> principal = principalMapper.queryOne(userId);
        if (null != principal){
            return principal;
        }
        return null;
    }

    /**
     * @description:
     *   修改负责人信息
     * @params: [principal]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/17 22:42
     */
    public Boolean updateList(Principal principal){
        //获取时间
        Date date = new Date();
        //设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        //获取负责人信息
        Principal principal1 = principal.setId(principal.getId())
                .setType(principal.getType())
                .setName(principal.getName())
                .setIdNumber(principal.getIdNumber())
                .setAge(principal.getAge())
                .setSex(principal.getSex())
                .setMajor(principal.getMajor())
                .setDuty(principal.getDuty())
                .setModifyTime(format);
        if (null != principal1){
            int i = principalMapper.updateList(principal1);
            if (i>0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
}
