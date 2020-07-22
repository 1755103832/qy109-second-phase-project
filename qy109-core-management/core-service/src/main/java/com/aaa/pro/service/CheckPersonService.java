package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.CheckPersonMapper;
import com.aaa.pro.model.CheckPerson;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @Author zyb
 * @Date Create in 2020/7/20 17:02
 * @Description
 **/
@Service
public class CheckPersonService extends BaseService<CheckPerson> {

    @Autowired
    private CheckPersonMapper checkPersonMapper;

    /**
     * @Author zyb
     * @Description 根据抽查比例分页查询抽查人员信息
     * @Date 2020/7/20 17:04
     * @Param [random, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.CheckPerson>
     **/
    public PageInfo<CheckPerson> selectByRatioPage(Double random,
                                                   Integer pageNum,
                                                   Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CheckPerson> units = checkPersonMapper.selectByRatioPage();
        if (units.size() > 0) {
            // 调用静态方法shuffle，让查询到的数据重新洗牌
            Collections.shuffle(units);
            // 调用ceil静态方法，把list数据的顺序按小数向上去整，重新排序
            double ceil = Math.ceil(units.size() * random);
            List<CheckPerson> lists = new ArrayList<>();
            // 把重新打乱的顺序进行for循环遍历，依次添加到list里面
            for (int i = 0; i < ceil; i++) {
                lists.add(units.get(i));
            }
            // 最后把lists放入分页，返回数据
            return new PageInfo<>(lists);
        } else {
            return null;
        }
    }

    /**
     * @Author zyb
     * @Description 新增抽查人员信息
     * @Date 2020/7/20 18:12
     * @Param [checkPerson]
     * @Return Boolean
     **/
    public Integer addCheckPersonInfo(CheckPerson checkPerson) {
//        return null != checkPerson && StringUtils.isNotEmpty(checkPerson.toString()) ?
//                Math.max(checkPersonMapper.addCheckPersonInfo(checkPerson), 0) : 0;
        if (null != checkPerson && StringUtils.isNotEmpty(checkPerson.toString())) {
            int i = checkPersonMapper.insert(checkPerson);
            return Math.max(i, 0);
        } else {
            return 0;
        }
    }

    /**
     * @Author zyb
     * @Description 修改抽查人员信息
     * @Date 2020/7/20 20:21
     * @Param [checkPerson]
     * @Return java.lang.Integer
     **/
    public Integer updateCheckPersonInfo(CheckPerson checkPerson) {
        if (null != checkPerson && StringUtils.isNotEmpty(checkPerson.toString())) {
            Integer integer = super.updateByPrimaryKeySelective(checkPerson);
            if (integer > 0) {
                return integer;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * @Author zyb
     * @Description 通过主键id删除抽查人员信息
     * @Date 2020/7/20 20:40
     * @Param [id]
     * @Return java.lang.Integer
     **/
    public Integer delCheckPersonInfoById(Long id) {
        if (null != id && StringUtils.isNotEmpty(id.toString())) {
            int i = checkPersonMapper.deleteByPrimaryKey(id);
            return Math.max(i, 0);
        } else {
            return 0;
        }
    }
}
