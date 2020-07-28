package com.aaa.pro.controller;


import com.aaa.pro.base.BaseService;
import com.aaa.pro.base.CommonController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.User;
import com.aaa.pro.service.DeptService;
import com.aaa.pro.service.UserService;
import com.aaa.pro.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.aaa.pro.staticproperties.CodeFormatProperties.CODE;
import static com.aaa.pro.staticproperties.CodeFormatProperties.DATA;
import static com.aaa.pro.status.CrudStatus.*;

/**
 * @Company: com.aaa.jkm
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 10:39 2020/7/16
 * @description： 用户的数据返回
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController extends CommonController<User> {
    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    /**
     * @Author: jkm
     * @Description:    查询所有用户信息
     * @Date: 20:58 2020/7/21
     * @param: []
     * @return java.util.Map
     */
    @GetMapping ("/selectAllUser")
    public Map selectAllUser(){
        return  userService.selectAll();
    }
    
    /**
     * @Author: jkm
     * @Description:    新增用户
     * @Date: 20:59 2020/7/27
     * @param: [user]
     * @return com.aaa.pro.base.ResultData
     */
    @PostMapping("/addUser")
    public ResultData addUser(User user){

        Map<String, Object> addResult = userService.addUser(user);
        if (INSERT_DATA_SUCCESS.getCode().equals(addResult.get(CODE))){
            return super.insertSuccess();
        }else{
            return super.insertFailed();
        }
    }
    @PostMapping("/deleteUserById")
    public Boolean deleteUserById( Long userId){


        return userService.deleteUserByUserId(userId);

    }

    /**
     * @Description: 批量删除用户
     * @Author: jkm
     * @Date: 2020/7/21 20:51
     * @Param: [ids, tokenId]
     * @return: com.aaa.pro.base.ResultData
     */
    @GetMapping("/delUser")
    public ResultData delUser(List<Long> ids){
        Map<String, Object> resultMap = userService.delUser(ids);
        if (DELETE_DATA_SUCCESS.getCode().equals(resultMap.get(CODE))){
            return super.deleteSuccess();
        }else{
            return super.deleteFailed();
        }
    }



    /**
     * @Description: 修改员工信息
     * @Author: jkm
     * @Date: 2020/7/21 15:56
     * @Param: [user]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/updateUser")
    public ResultData updateUser(@RequestBody User user){
        Map<String, Object> resultMap = userService.updateUser(user);
        if (UPDATE_DATA_SUCCESS.getCode().equals(resultMap.get(CODE))){
            return super.updateSuccess();
        }else{
            return super.updateFailed();
        }
    }


    /**
     * @Description: 导出用户信息
     * @Author: jkm
     * @Date: 2020/721 16:26
     * @Param: []
     * @return: com.aaa.pro.base.ResultData
     */
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        Map<String, Object> map = userService.selectAll();
        if (QUERY_DATA_SUCCESS.getCode().equals(map.get(CODE))){
            List<User> users = (List<User>) map.get(DATA);
            //不为空，开始进行导出
            if (null != users && !users.isEmpty()){
                //list存放表格数据
                List<List<String>> excelData = new ArrayList<List<String>>();
                if(null != users){
                    //表格头
                    List<String> headList = new ArrayList<String>();
                    headList.add("用户ID");
                    headList.add("用户名");
                    headList.add("部门ID");
                    headList.add("邮箱");
                    headList.add("联系电话");
                    headList.add("状态");
                    headList.add("创建时间");
                    headList.add("修改时间");
                    headList.add("最近访问时间");
                    headList.add("性别");
                    headList.add("描述");
                    headList.add("用户类型");
                    //把表头放入表格数据中
                    excelData.add(headList);
                    //遍历表格数据并放入excelData
                    for (User user : users) {
                        List<String> list = new ArrayList<String>();
                        list.add(String.valueOf(user.getId()));
                        list.add(String.valueOf(user.getUsername()));
                        list.add(String.valueOf(user.getDeptId()));
                        list.add(String.valueOf(user.getEmail()));
                        list.add(String.valueOf(user.getMobile()));
                        if ("0".equals(user.getStatus())){
                            list.add("锁定");
                        }else if ("1".equals(user.getStatus())){
                            list.add("有效");
                        }
                        list.add(String.valueOf(user.getCreateTime()));
                        list.add(String.valueOf(user.getModifyTime()));
                        list.add(String.valueOf(user.getLastLoginTime()));
                        if ("0".equals(user.getSsex())){
                            list.add("男");
                        }else if ("1".equals(user.getSsex())){
                            list.add("女");
                        }else if ("2".equals(user.getSsex())){
                            list.add("保密");
                        }
                        list.add(String.valueOf(user.getDescription()));
                        if ("0".equals(user.getType())){
                            list.add("单位用户");
                        }else if ("1".equals(user.getType())){
                            list.add("审核用户");
                        }else if ("2".equals(user.getType())){
                            list.add("管理员");
                        }
                        //把数据放入excelData
                        excelData.add(list);
                    }
                }
                String sheetName = "用户信息";
                String fileName = "用户信息表";
                try {
                    ExcelUtils.exportExcel(response, excelData, sheetName, fileName, 12);
                } catch (IOException e) {
                    log.error("用户信息数据导出失败！");
                }

            }
        }else{
            log.error("用户管理中的导出数据出错！");
        }
    }

    /**
     * @Description: 带条件查询用户信息
     * @Author: jkm
     * @Date: 2020/7/21 22:31
     * @Param: [map]
     * @return: com.aaa.pro.base.ResultData
     */
    @PostMapping("/selectUser")
    public ResultData selectUserAll(String userName, DeptService deptService){
        Map<String, Object> userAll = userService.selectUserAll(userName,deptService);
        if (QUERY_DATA_SUCCESS.getCode().equals(userAll.get(CODE))){
            return super.querySuccess(userAll);
        }else if (QUERY_DATA_FAILED.getCode().equals(userAll.get(CODE))){
            return super.queryFailed();
        }else{
            return super.queryFailed(QUERY_DATA_FAILED.getMessage());
        }
    }


    

    @Override
    public BaseService getBaseService() {
        return userService;
    }
}
