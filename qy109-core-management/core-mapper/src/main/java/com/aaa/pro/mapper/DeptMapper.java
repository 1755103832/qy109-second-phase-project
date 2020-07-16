package com.aaa.pro.mapper;


import com.aaa.pro.model.Dept;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DeptMapper extends Mapper<Dept> {

    /**
    * @description:
     * 查询部门信息，根据主键id查询部门的信息
    * @params: [deptId]
    * @return: com.aaa.pro.model.Dept
    * @author: Wen
    * @date: 2020/7/14 8:46
    */
    Dept selectDeptByDeptId(Integer deptId);


     /**
     * @description:
     *  查询所有的部门
     * @params: [parentId]
     * @return: java.util.List<com.aaa.pro.model.Dept>
     * @author: Wen
     * @date: 2020/7/14 8:46
     */
    List<Dept> selectDeptByParentId(Integer parentId);


   /**
    * @description: 查询-动态sql查询条件：部门名称 创建时间区间
    * @params: [map]
    * @return: java.util.List<com.aaa.pro.model.Dept>
    * @author: Wen
    * @date: 2020/7/14 8:46
    */
    List<Dept> selectDeptInfoByField(Map map);
    

    /**
     * @description:
     *   新增部门信息
     * @params: [dept]
     * @return: int
     * @author: Wen
     * @date: 2020/7/14 8:46
     */
    int insertDept(Dept dept);


}