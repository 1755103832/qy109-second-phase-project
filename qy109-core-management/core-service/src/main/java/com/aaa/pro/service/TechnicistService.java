package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.ResourceMapper;
import com.aaa.pro.mapper.TechnicistMapper;
import com.aaa.pro.model.Technicist;
import com.aaa.pro.properties.FtpProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Service
public class TechnicistService extends BaseService<Technicist> {
    @Autowired
    private TechnicistMapper technicistMapper;


    public List<Technicist> queryTechnicist(Long userId){
        //根据userid去查询技术人员信息
        List<Technicist> technicist = technicistMapper.queryTechnicist(userId);
        if (null != technicist){
            //不为空返回信息
            return technicist;
        }
        return null;
    }

   /**
    * @description:
    *   修改技术人员信息
    * @params: [technicist]
    * @return: java.lang.Boolean
    * @author: Wen
    * @date: 2020/7/18 16:19
    */
    public Boolean updataTechnicist(Technicist technicist){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(date);
        Technicist technicist1 = technicist.setId(technicist.getId())
                .setName(technicist.getName())
                .setIdNumber(technicist.getIdNumber())
                .setMajorType(technicist.getMajorType())
                .setSex(technicist.getSex())
                .setAge(technicist.getAge())
                .setMajor(technicist.getMajor())
                .setDuty(technicist.getDuty())
                .setTitleMajor(technicist.getTitleMajor())
                .setModifyTime(format);
        if (null != technicist1){
            int i = technicistMapper.updataTechnicist(technicist1);
            if (i>0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

}



