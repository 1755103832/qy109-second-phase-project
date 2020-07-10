package com.aaa.pro.base;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author project
 * @Date Create in 2020/7/9 16:21
 * @Description
 **/
public abstract class CommonController<T> extends BaseController {

    /**
     * getBaseService
     *
     * @return
     */
    public abstract BaseService<T> getBaseService();

    /**
     * @Author project
     * @Description 钩子函数
     * 在新增之前去执行某些操作
     * <p>
     * 下单操作:
     * 需求
     * 在购物车中当点击立即下单按钮的时候--->跳转下单页面(选择地址，选择优惠券...)
     * 把购物车中的这个商品删除
     * deleteCart(List<Integer> id);--->是优先于insertOrder前置执行
     * <p>
     * insertOrder(Order oder);
     * @Date 2020/7/9 16:26
     * @Param [map]
     * @Return void
     **/
    protected void beforeAdd(Map map) {
        // TODO: 2020/7/9 AddMethod Before to do something 
    }

    /**
     * @Author project
     * @Description 钩子函数
     * 是在新增之后去执行
     * <p>
     * int result = insertOrder(Order order)
     * if(result > 0) {
     * insertOrderDetail(OrderDetail orderDetail);
     * }
     * @Date 2020/7/9 16:27
     * @Param [map]
     * @Return void
     **/
    protected void afterAdd(Map map) {
        // TODO: 2020/7/9 AddMethod After to do something 
    }

    /**
     * @Author project
     * @Description 通用的新增方法
     * 因为咱们目前市面上所有的公司实现的全是异步了
     * 也就是说前端向后端所传递的数据都是json格式
     * 之前在controller的方法中接收固定的实体类，是因为你知道前端给你传递的类型就是这个实体类
     * 但是既然要做通用，前端所传递的类型就不会固定了--->所以使用Map类型来统一接收
     * @Date 2020/7/9 16:30
     * @Param [map]
     * @Return com.aaa.pro.base.ResultData
     **/
    public ResultData add(@RequestBody Map map) {
        // 因为根据咱们的封装规则，在service中是需要传递泛型的，就意味着service需要接收固定的实体类
        // 但是controller是一个Map类型
        beforeAdd(map);
        // 1.Map转实体类
        T instance = getBaseService().newInstance(map);
        // 2.通用service
        Integer addResult = getBaseService().insert(instance);
        if (addResult > 0) {
            afterAdd(map);
            return super.insertSuccess();
        }
        return super.insertFailed();
    }

    /**
     * @Author project
     * @Description 删除操作
     * @Date 2020/7/9 16:32
     * @Param [map]
     * @Return com.aaa.pro.base.ResultData
     **/
    public ResultData delete(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        Integer deleteResult = getBaseService().deleteByPrimaryKey(instance);
        if (deleteResult > 0) {
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }

    /**
     * @Author project
     * @Description 批量删除
     * @Date 2020/7/9 16:33
     * @Param [ids]
     * @Return com.aaa.pro.base.ResultData
     **/
    public ResultData batchDelete(@RequestParam("ids[]") Integer[] ids) {
        Integer deleteResult = getBaseService().batchDeleteByIds(Arrays.asList(ids));
        if (deleteResult > 0) {
            return super.deleteSuccess();
        }
        return super.deleteFailed();
    }

    /**
     * @Author zyb
     * @Description 修改数据
     * @Date 2020/7/9 17:22
     * @Param [map]
     * @Return com.aaa.pro.base.ResultData
     **/
    public ResultData updateByPrimaryKeySelective(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        Integer i = getBaseService().updateByPrimaryKeySelective(instance);
        if (i > 0) {
            return super.updateSuccess();
        }
        return super.updateFailed();
    }

    /**
     * @Author zyb
     * @Description 查询一条数据
     * @Date 2020/7/9 17:28
     * @Param [map]
     * @Return com.aaa.pro.base.ResultData
     **/
    public ResultData selectOne(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        T t = getBaseService().selectOne(instance);
        if (null != t && !"".equals(t)) {
            return super.querySuccess();
        }
        return super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 查询多条数据
     * @Date 2020/7/9 21:17
     * @Param [map]
     * @Return com.aaa.pro.base.ResultData
     **/
    public ResultData selectList(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        List<T> list = getBaseService().selectList(instance);
        if (list.size() > 0) {
            return super.querySuccess();
        }
        return super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 带条件的分页查询
     * @Date 2020/7/10 16:00
     * @Param [map, pageNo, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    public ResultData getListByPage(@RequestBody Map map, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        T t = getBaseService().newInstance(map);
        PageInfo<T> pageInfo = getBaseService().selectListByPage(t, pageNo, pageSize);
        List<T> resultList = pageInfo.getList();
        if (resultList.size() > 0) {
            return super.querySuccess(pageInfo);
        }
        return super.queryFailed();
    }

    /**
     * @Author zyb
     * @Description 不带条件的分页查询
     * @Date 2020/7/10 16:06
     * @Param [pageNo, pageSize]
     * @Return com.aaa.pro.base.ResultData
     **/
    public ResultData getListByPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<T> pageInfo = getBaseService().selectListByPage(null, pageNo, pageSize);
        List<T> resultList = pageInfo.getList();
        if (resultList.size() > 0) {
            return super.querySuccess(pageInfo);
        }
        return super.queryFailed("未找到查询结果");
    }


    /**
     * @Author zyb
     * @Description 防止数据不安全，所以不能直接在controller某个方法中直接接收HttpServletRequest对象
     * 必须要从本地当前线程中获取request对象
     * @Date 2020/7/9 21:18
     * @Param []
     * @Return javax.servlet.http.HttpServletRequest
     **/
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * @Author zyb
     * @Description 获取当前客户端的session对象(如果不存在 ， 则会重新创建一个)
     * @Date 2020/7/9 21:18
     * @Param []
     * @Return javax.servlet.http.HttpSession
     **/
    public HttpSession getSession() {
        return getServletRequest().getSession();
    }

    /**
     * @Author zyb
     * @Description 获取当前客户端的session对象(如果不存在 ， 则直接返回为null)
     * @Date 2020/7/9 21:18
     * @Param []
     * @Return javax.servlet.http.HttpSession
     **/
    public HttpSession getExistSession() {
        return getServletRequest().getSession(false);

    }
}
