package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.DeptMapper;
import com.aaa.pro.model.Dept;

import com.aaa.pro.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class DeptService extends BaseService<Dept> {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private RedisService<Dept> redisService;


    /**
     * 递归查询根据 parentId（父id）查询该部门及其子部门
     * @param parentId
     * @return
     */
    public List<Dept> selectAllDeptByParentId(Integer parentId) {
        // 调用 deptMapper 中的 selectDeptByParentId 方法，查询父部门信息
        List<Dept> allDept = deptMapper.selectAllDeptByParentId(parentId);

        // 判断 根据父id查询的结果是否为空
        if(allDept.size()>0 && null != allDept){
            // 说明结果不为空，将结果进行遍历，获取子部门信息
            for(Dept dept : allDept){
                // 获取父部门的id，作为子部门的父id进行查询
                Integer id1 = dept.getDeptId();
                List<Dept> children = deptMapper.selectAllDeptByParentId(id1);

                // 将得到的结果放到children中，子部门信息
                dept.setChildren(children);
            }
            // 返回 查询的部门信息
            return allDept;
        }else {
            // 查询失败，返回null
            return null;
        }
    }


    /**
     * @description:
     *  查询部门信息-动态sql查询-条件：部门名称 创建时间区间
     * @params: [map]
     * @return: java.util.List<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/14 8:50
     */
    public List<Dept> selectDeptInfoByField(Map map) {
        List<Dept> deptList = null;
        try {
            // 调用 deptMapper 中的 selectDeptInfoByField 方法，根据条件查询部门信息
            deptList = deptMapper.selectDeptInfoByField(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if(null != deptList && deptList.size() > 0){
            // 查询成功，返回 查询的部门信息
            return deptList;
        }else {
            // 查询失败，返回null
            return null;
        }
    }


    /**
     * @description:
     *   查询部门信息，根据主键id查询部门的信息
     * @params: [deptId]
     * @return: com.aaa.pro.model.Dept
     * @author: Wen
     * @date: 2020/7/16 8:51
     */
    public Dept selectDeptByDeptId(Integer deptId) {
        // 调用 deptMapper 中的 selectDeptByDeptId 方法，查询部门信息
        Dept deptByDeptId = deptMapper.selectDeptByDeptId(deptId);

        // 判断 结果是否为空
        if(null != deptByDeptId && !"".equals(deptByDeptId)){
            // 返回 查询的部门信息
            return deptByDeptId;
        }else {
            // 查询失败，返回null
            return null;
        }
    }


   /**
    * @description:
    *   新增部门信息
    * @params: [dept]
    * @return: java.lang.Boolean
    * @author: Wen
    * @date: 2020/7/16 8:51
    */
    public Boolean insertDept(Dept dept) {
        // 判断 前端传值是否为空
        if (null != dept && !"".equals(dept)) {
            int i = 0;
            try {
                // 获取当前时间
                Date date = new Date();
                // 设置日期格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(date);

                // 获取前端传递的参数，放入 dept1 中
                Dept dept1 = dept.setDeptName(dept.getDeptName())
                        .setOrderNum(dept.getOrderNum())
                        .setParentId(dept.getParentId())
                        .setCreateTime(format);

                // 调用 通用 BaseService 中的 add 方法，新增部门信息
                i = super.insert(dept1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 判断 受影响行数是否大于0，新增是否成功
            if (i>0){
                // 新增成功，返回true
                return true;
            }else {
                // 新增失败，返回false
                return false;
            }
        }else {
            // 前端传值失败，返回false
            return false;

        }

    }


     /**
     * @description:
     *   删除部门信息 根据主键删除
     * @params: [deptId]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/16 8:51
     */
    public Boolean deleteDeptByPrimaryKey(Dept deptId) {
        // 判断前端传值是否为空
        if (null != deptId && !"".equals(deptId)) {
            Integer delete = null;

            try {
                // 说明前端传值成功，调用父类BaseService的delete方法，执行删除操作
                delete = deleteByPrimaryKey(deptId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 判断 受影响行数是否大于0，删除是否成功
            if (delete > 0) {
                // 删除成功，返回true
                return true;
            }else {
                // 删除失败，返回false
                return false;
            }
        }else {
            // 前端传参失败，返回false 删除失败
            return false;
        }
    }


    /**
     * @description:
     *  批量删除部门信息 调用父类的批量删除方法（根据主键），执行删除操作
     * @params: [ids]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/14 9:18
     */
    public Boolean batchDeleteByPrimaryKey(List<Object> ids) {
        // 判断 前端传值是否为空
        if (null != ids && !"".equals(ids)) {
            Integer batchDelete = null;

            try {
                // 调用父类的批量删除方法
                batchDelete = super.batchDeleteByIds(ids);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 判断 受影响行数是否大于0，删除是否成功
            if (batchDelete > 0) {
                // 删除成功，返回true
                return true;
            }else {
                // 删除失败，返回false
                return false;
            }
        }else {
            // 前端传参失败，返回false 删除失败
            return false;
        }
    }


    /**
     * @description:
     *  修改部门信息，根据主键修改
     * @params: [dept]
     * @return: java.lang.Boolean
     * @author: Wen
     * @date: 2020/7/14 9:17
     */
    public Boolean updateDeptByPrimaryKey(Dept dept) {
        // 判断 前端传值是否为空
        if (null != dept && !"".equals(dept)) {
            Integer update = null;
            try {
                // 获取当前时间
                Date date = new Date();
                // 设置日期格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(date);

                // 获取前端传递的参数，放入 dept1 中
                Dept dept1 = dept.setDeptId(dept.getDeptId())
                        .setDeptName(dept.getDeptName())
                        .setOrderNum(dept.getOrderNum())
                        .setParentId(dept.getParentId())
                        .setModifyTime(format);

                // 调用父类的update（updateByPrimaryKeySelective）方法，更新部门信息
                update = super.updateByPrimaryKeySelective(dept1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 判断 受影响行数是否大于0，修改是否成功
            if (update > 0) {
                // 说明修改成功，返回true
                return true;
            }else {
                // 修改失败，返回false
                return false;
            }
        }else {
            // 前端传参失败，返回false 修改失败
            return false;
        }
    }



}
