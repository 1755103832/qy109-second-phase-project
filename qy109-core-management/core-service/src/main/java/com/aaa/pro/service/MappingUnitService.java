package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.MappingUnitMapper;
import com.aaa.pro.model.MappingUnit;
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
 * @Date Create in 2020/7/18 18:52
 * @Description
 **/
@Service
public class MappingUnitService extends BaseService<MappingUnit> {

    @Autowired
    private MappingUnitMapper mappingUnitMapper;

    /**
     * @Author zyb
     * @Description 查询白名单人员信息
     * @Date 2020/7/18 18:55
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    public PageInfo<MappingUnit> selectByUnitStatus(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingUnit> units = mappingUnitMapper.selectByUnitStatus();
        return StringUtils.isNotEmpty(units.toString()) && units.size() > 0 ? new PageInfo<>(units) : null;
    }

    /**
     * @Author zyb
     * @Description 查询黑名单人员信息
     * @Date 2020/7/18 19:05
     * @Param [pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    public PageInfo<MappingUnit> selectByUnitStatus2(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MappingUnit> units = mappingUnitMapper.selectByUnitStatus2();
        return StringUtils.isNotEmpty(units.toString()) ? new PageInfo<>(units) : null;
    }

    /**
     * @Author zyb
     * @Description 根据抽查比例查询单位信息
     * @Date 2020/7/19 9:07
     * @Param [random, ownedDistrict, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.MappingUnit>
     **/
    public PageInfo<MappingUnit> selectByRatioAndType(Double random,
                                                      String ownedDistrict,
                                                      Integer pageNum,
                                                      Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotEmpty(ownedDistrict)) {
            List<MappingUnit> units = mappingUnitMapper.selectByRatioAndType(ownedDistrict);
            if (units.size() > 0) {
                // 调用静态方法shuffle，让查询到的数据重新洗牌
                Collections.shuffle(units);
                // 调用ceil静态方法，把list数据的顺序按小数向上去整，重新排序
                double ceil = Math.ceil(units.size() * random);
                List<MappingUnit> lists = new ArrayList<>();
                // 把重新打乱的顺序进行for循环遍历，依次添加到list里面
                for (int i = 0; i < ceil; i++) {
                    lists.add(units.get(i));
                }
                // 最后把lists放入分页，返回数据
                return new PageInfo<>(lists);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
