package com.aaa.pro.base;

import com.aaa.pro.utils.Map2BeanUtils;
import com.aaa.pro.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.aaa.pro.staticproperties.SortOrder.*;

/**
 * @Author project
 * @Date Create in 2020/7/9 8:32
 * @Description
 **/
public abstract class BaseService<T> {

    /**
     * 本地缓存池 全局变量，缓存子类的泛型类型
     */
    private Class<T> cache = null;

    @Autowired
    private Mapper<T> mapper;

    /**
     * @Author project
     * @Description 上面的这个Mapper为了保证安全性，必须要是private类型，那么他的子类(UserService)就调用不到
     * 所以需要提供方法给子类用
     * @Date 2020/7/9 8:35
     * @Param []
     * @Return tk.mybatis.mapper.common.Mapper
     **/
    protected Mapper getMapper() {
        return mapper;
    }

    /**
     * @Author project
     * @Description 新增数据 insert
     * @Date 2020/7/9 15:29
     * @Param [t]
     * @Return java.lang.Integer
     **/
    public Integer insert(T t) {
        return mapper.insert(t);
    }

    /**
     * @Author project
     * @Description 新增数据 insertSelective
     * @Date 2020/7/9 15:29
     * @Param [t]
     * @Return java.lang.Integer
     **/
    public Integer insertSelective(T t) {
        return mapper.insertSelective(t);
    }

    /**
     * @Author project
     * @Description 根据主键进行删除
     * @Date 2020/7/9 15:31
     * @Param [t]
     * @Return java.lang.Integer
     **/
    public Integer deleteByPrimaryKey(T t) {
        return mapper.deleteByPrimaryKey(t);
    }

    /**
     * @Author project
     * @Description 获取子类泛型类型
     * @Date 2020/7/9 15:37
     * @Param []
     * @Return java.lang.Class<T>
     **/
    public Class<T> getTypeArgument() {
        if (null == cache) {
            cache = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return cache;
    }

    /**
     * @Author project
     * @Description 根据主键进行批量删除
     * @Date 2020/7/9 15:39
     * @Param [ids]
     * @Return java.lang.Integer
     **/
    public Integer batchDeleteByIds(List<Object> ids) {
        // delete * from user where 1 = 1 and id in (1,2,3,4,5,6,7,8)
        // andIn("id")--->id就是数据库中的主键名称
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", ids)).build();
        return mapper.deleteByExample(example);
    }

    /**
     * @Author project
     * @Description 更新操作
     * @Date 2020/7/9 15:40
     * @Param [t]
     * @Return java.lang.Integer
     **/
    public Integer updateByPrimaryKeySelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * @Author project
     * @Description update username = ?  from user where id in (1,2,3,4,5,6,7)
     * @Date 2020/7/9 15:43
     * @Param [t, ids]
     * @Return java.lang.Integer
     **/
    public Integer batchUpdate(T t, Integer[] ids) {
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t, example);
    }

    /**
     * @Author project
     * @Description 查询一条数据
     * 形参中的t所传递的数据--->主键，唯一键(username, phone number....)
     * @Date 2020/7/9 15:45
     * @Param [t]
     * @Return T
     **/
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    /**
     * @Author project
     * @Description 查询集合, 条件查询
     * @Date 2020/7/9 16:09
     * @Param [t]
     * @Return java.util.List<T>
     **/
    public List<T> selectList(T t) {
        return mapper.select(t);
    }

    /**
     * @Author project
     * @Description 查询一条数据
     * 可以排序(orderByFiled:ASC,DESC)
     * fields:不只是代表唯一键
     * password
     * age
     * address
     * select * from user where password = xxxx and age = xx and address = xxx
     * @Date 2020/7/9 16:02
     * @Param [where, orderByField, fields]
     * @Return T
     **/
    public T selectOneByField(Sqls where, String orderByField, String... fields) {
        return selectByFields(null, null, where, orderByField, null, fields).get(0);
    }

    /**
     * @Author project
     * @Description 通过条件查询一个列表
     * @Date 2020/7/9 16:05
     * @Param [where, orderByField, fields]
     * @Return java.util.List<T>
     **/
    public List<T> selectListByField(Sqls where, String orderByField, String... fields) {
        return selectByFields(null, null, where, orderByField, null, fields);
    }

    /**
     * @Author project
     * @Description 实现条件查询的分页
     * @Date 2020/7/9 16:08
     * @Param [pageNo, pageSize, where, orderByField, fields]
     * @Return com.github.pagehelper.PageInfo<T>
     **/
    public PageInfo<T> selectListByPageAndField(Integer pageNo, Integer pageSize, Sqls where, String orderByField, String... fields) {
        return new PageInfo<>(selectByFields(pageNo, pageSize, where, orderByField, null, fields));
    }

    /**
     * @Author project
     * @Description 查询集合，分页查询
     * @Date 2020/7/9 16:12
     * @Param [t, pageNo, pageSize]
     * @Return com.github.pagehelper.PageInfo<T>
     **/
    public PageInfo<T> selectListByPage(T t, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> select = mapper.select(t);
        return new PageInfo<>(select);
    }

    /**
     * @Author project
     * @Description Map转换实体类型
     * @Date 2020/7/9 16:15
     * @Param [map]
     * @Return T
     **/
    public T newInstance(Map map) {
        return (T) Map2BeanUtils.map2Bean(map, getTypeArgument());
    }

    /**
     * @Author project
     * @Description 实现查询通用
     * 不但可以作用于分页，还可以作用于排序，还能作用于多条件查询
     * orderByFiled:是所要排序的字段
     * @Date 2020/7/9 15:59
     * @Param [pageNo, pageSize, where, orderByField, orderWord, fields]
     * @Return java.util.List<T>
     **/
    private List<T> selectByFields(Integer pageNo, Integer pageSize, Sqls where, String orderByField, String orderWord, String... fields) {
        Example.Builder builder = null;

        if (null == fields || fields.length == 0) {
            // 查询所有数据
            builder = Example.builder(getTypeArgument());
        } else {
            builder = Example.builder(getTypeArgument()).select(fields);
        }

        if (where != null) {
            // 说明有用户自定义的where语句条件
            builder = builder.where(where);
        }

        if (orderByField != null) {
            // 说明我需要对某个字段进行排序
            if (DESC.equals(orderWord.toUpperCase())) {
                // 说明需要倒序
                builder = builder.orderByDesc(orderByField);
            } else if (ASC.equals(orderWord.toUpperCase())) {
                // 说明需要正序
                builder = builder.orderByAsc(orderByField);
            } else {
                // 说明不需要进行排序，就默认正序
                builder = builder.orderByAsc(orderByField);
            }
        }

        Example example = builder.build();
        // 实现分页
        if (pageNo != null & pageSize != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        return getMapper().selectByExample(example);
    }

    /**
     *
     * @description:
     *    获取子类泛型类型
     * @params:  * @param
     * @return: java.lang.Class<T>
     * @author: Wen
     * @date: 2020/7/15 15:34
     */
    public Class<T> getTypeArguement() {
        if(null == cache) {
            cache = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return cache;
    }

    /**
     * @Author project
     * @Description 获取spring容器/获取spring的上下文
     * 在项目开始运行的时候，会去加载spring配置，
     * 如果你们项目需要在项目启动的时候也加载自己的配置文件
     * 在spring的源码中有一个必须要看的方法(init())
     * init()--->就是在项目启动的时候去加载spring的配置
     * 如果你的项目中也需要把某一些配置一开始就托管给spring
     * 需要获取到spring的上下文(ApplicationContext)
     * @Date 2020/7/9 16:17
     * @Param []
     * @Return org.springframework.context.ApplicationContext
     **/
    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }
}
