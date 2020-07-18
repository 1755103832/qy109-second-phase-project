package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.DictMapper;
import com.aaa.pro.model.Dict;
import com.aaa.pro.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/16 10:10
 * @Description
 **/
@Service
public class DictService extends BaseService<Dict> {

    @Autowired
    private DictMapper dictMapper;

    /**
     * @Author zyb
     * @Description select all dict info
     * @Date 2020/7/16 10:16
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    public List<Dict> selectAllDictInfo() {
        List<Dict> dictList = dictMapper.selectAll();
        return null != dictList && StringUtils.isNotEmpty(dictList.toString()) ? dictList : null;
    }

    /**
     * @Author zyb
     * @Description 新增字典表信息
     * @Date 2020/7/16 11:44
     * @Param [dict]
     * @Return java.lang.Integer
     **/
    public Integer addDictInfo(Dict dict) {
        return null != dict && StringUtils.isNotEmpty(dict.toString()) ?
                Math.max(dictMapper.insert(dict), 0) : 0;
    }

    /**
     * @Author zyb
     * @Description 通过字典表id批量删除数据
     * @Date 2020/7/16 15:06
     * @Param [dictIds]
     * @Return java.lang.Integer
     **/
    @Override
    public Integer batchDeleteByDictIds(List<Integer> dictIds) {
        if (null != dictIds && StringUtils.isNotEmpty(dictIds.toString())) {
            Integer ids = super.batchDeleteByDictIds(dictIds);
            if (ids > 0) {
                return ids;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * @Author zyb
     * @Description 通过dictId修改字典表信息
     * @Date 2020/7/16 15:26
     * @Param [dict]
     * @Return java.lang.Integer
     **/
    public Integer updateByDictId(Dict dict) {
        return null != dict && StringUtils.isNotEmpty(dict.toString()) ?
                Math.max(dictMapper.updateByPrimaryKey(dict), 0) : 0;
    }

    /**
     * @Author zyb
     * @Description 通过主键dictId查询字典表数据
     * @Date 2020/7/16 16:03
     * @Param [dictId]
     * @Return com.aaa.pro.model.Dict
     **/
    public Dict selectOneByDictId(Long dictId) {
        if (dictId > 0L) {
            Dict dict = dictMapper.selectByPrimaryKey(dictId);
            if (StringUtils.isNotEmpty(String.valueOf(dictId))) {
                return dict;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @Author zyb
     * @Description 分页查询字典表数据
     * @Date 2020/7/16 17:19
     * @Param [dict, pageNum, pageSize]
     * @Return com.github.pagehelper.PageInfo<com.aaa.pro.model.Dict>
     **/
    public PageInfo<Dict> selectDictListByPage(Integer pageNum, Integer pageSize) {
        PageInfo<Dict> dictPageInfo = super.selectListByPage(pageNum, pageSize);
        return null != dictPageInfo && StringUtils.isNotEmpty(String.valueOf(dictPageInfo)) ? dictPageInfo : null;
    }

    /**
     * @Author zyb
     * @Description fuzzy query test
     * @Date 2020/7/16 20:54
     * @Param [tableName]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    public List<Dict> fuzzy2selectDictByTableName(String tableName) {
        if (StringUtils.isNotEmpty(tableName)) {
            List<Dict> dictList = dictMapper.fuzzy2selectDictByTableName(tableName);
            if (null != dictList && StringUtils.isNotEmpty(String.valueOf(dictList))) {
                return dictList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @Author zyb
     * @Description 分页查询字典表
     * @Date 2020/7/18 16:13
     * @Param [pageNum, pageSize]
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    public PageInfo<Dict> queryDictByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dict> dictList = dictMapper.queryDictByPage();
        return dictList.size() > 0 ? new PageInfo<>(dictList) : null;
    }


    /**
     * @Author zyb
     * @Description 通过key分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    public PageInfo<Dict> queryDictByKeyPage(Long keyy, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dict> dictList = dictMapper.queryDictByKeyPage(keyy);
        return dictList.size() > 0 ? new PageInfo<>(dictList) : null;
    }

    /**
     * @Author zyb
     * @Description 通过value分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    public PageInfo<Dict> queryDictByValuePage(String valuee, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dict> dictList = dictMapper.queryDictByValuePage(valuee);
        return dictList.size() > 0 ? new PageInfo<>(dictList) : null;
    }

    /**
     * @Author zyb
     * @Description 通过字段名分页查询字典表
     * @Date 2020/7/18 16:32
     * @Param []
     * @Return java.util.List<com.aaa.pro.model.Dict>
     **/
    public PageInfo<Dict> queryDictByFieldNamePage(String fieldName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dict> dictList = dictMapper.queryDictByFieldNamePage(fieldName);
        return dictList.size() > 0 ? new PageInfo<>(dictList) : null;
    }
}
