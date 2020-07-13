package com.aaa.pro.service;

import com.aaa.pro.base.BaseService;
import com.aaa.pro.mapper.LoginLogsMapper;
import com.aaa.pro.model.LoginLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 15:37
 * @Description
 **/
@Service
public class LoginLogsService extends BaseService<LoginLogs> {

    @Autowired
    private LoginLogsMapper loginLogsMapper;

    /**
     * @Author zyb
     * @Description 获取登录信息(ip, address, loginTime)
     * @Date 2020/7/13 15:52
     * @Param [username]
     * @Return int
     **/
    public int doLoginLogs(String username) {
        LoginLogs loginLogs = new LoginLogs();
        try {
            // 获取ip地址
            InetAddress localHost = InetAddress.getLocalHost();
            String ip = localHost.getHostAddress();
            // 获取登录地址
            String address = String.valueOf(InetAddress.getLoopbackAddress());
            // 设置日期格式
            Format datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = datetime.format(System.currentTimeMillis());
            loginLogs.setIp(ip).setUsername(username).setLoginTime(time).setLocation(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return loginLogsMapper.insert(loginLogs);
    }
}
