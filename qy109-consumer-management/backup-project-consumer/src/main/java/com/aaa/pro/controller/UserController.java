package com.aaa.pro.controller;

import com.aaa.pro.base.BaseController;
import com.aaa.pro.base.ResultData;
import com.aaa.pro.model.User;
import com.aaa.pro.service.IProjectService;
import feign.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @Company: com.aaa.jkm
 * @ProjectName: qy109-second-phase-project
 * @PackageName: com.aaa.pro.controller
 * @Author： jkm
 * @Date： Create in 18:34 2020/7/16
 * @description：
 */
@RestController
//@Api(value = "用户",tags = "用户操作接口")
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Description: 用户管理中新增用户
     * @Author: jkm
     * @Date: 2020/5/20 14:43
     * @Param: [user]
     * @return:
     */
    @PostMapping("/addUser")
//    @ApiOperation(value = "添加用户",notes = "用户管理的新增用户")
    public Boolean addUser(@RequestBody User user) {
        return iProjectService.addUser(user);
    }


    /**
     * @Description: 用户管理中删除用户
     * @Author: jkm
     * @Date: 2020/5/21 15:44
     * @Param: [ids]
     * @return:
     */
    @DeleteMapping("/delUser")
    @ApiOperation(value = "删除用户", notes = "用户管理的删除用户")
    public ResultData delUser(@RequestBody List<Long> ids) {
        return iProjectService.delUser(ids);
    }


    /**
     * @Description: 用户管理中修改用户信息
     * @Author: jkm
     * @Date: 2020/5/21 15:47
     * @Param: [user]
     * @return:
     */
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息", notes = "用户管理的修改用户信息")
    public ResultData updateUser(@RequestBody User user) {
        return iProjectService.updateUser(user);
    }


    /**
     * @Description: 用户管理中的导出Excle
     * @Author: jkm
     * @Date: 2020/5/21 16:25
     * @Param: []
     */
    @GetMapping("/exportExcle")
    @ApiOperation(value = "导出Excle", notes = "用户管理的导出用户信息")
    public ResponseEntity<byte[]> exportExcle() {
        ResponseEntity<byte[]> result = null;
        Response response = iProjectService.exportExcle();
        Response.Body body = response.body();
        try (InputStream inputStream = body.asInputStream()) {
            // feign文件下载
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[10240];
            while (true) {
                int len = inputStream.read(buf);
                if (len < 0) {
                    break;
                }
                bos.write(buf, 0, len);
            }
            //网上也有这种将数据读入到byte[]里面的操作，但是会有问题，有可能一些数据没有读完整，导致最终下载的文件打不开，所以最好还是上面那种常规的读取操作
            //byte[] b = new byte[inputStream.available()];
            //inputStream.read(b);
            byte[] b = bos.toByteArray();
            HttpHeaders heads = new HttpHeaders();
            heads.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=user.xls");
            heads.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel;charset=utf-8");
            heads.add(HttpHeaders.CONNECTION, "close");
            result = new ResponseEntity<>(b, heads, HttpStatus.OK);
        } catch (IOException e) {

        }
        return result;
    }


    /**
     * @Description: 查询用户，带条件
     * @Author: jkm
     * @Date: 2020/5/21 22:54
     * @Param: [map]
     * @return:
     */
    @PostMapping("selectUser")
    ResultData selectUserAll(@RequestBody HashMap map) {
        return iProjectService.selectUserAll(map);
    }

}
