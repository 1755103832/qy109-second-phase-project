package com.aaa.pro.utils;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zyb
 * @Date Create in 2020/7/19 11:23
 * @Description 和ip地址有关的工具类
 **/
public class IpUtils {

    /**
     * unknown静态常量
     */
    private static final String UNKNOWN = "unknown";

    /**
     * 私有化构造方法
     */
    private IpUtils() {
    }

    /**
     * @Author zyb
     * @Description 获取客户端公网IP
     * @Date 2020/7/19 11:23
     * @Param []
     * @Return java.lang.String
     **/
    public static String getInternetIp() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com/";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            while ((read = in.readLine()) != null) {
                inputLine.append(read).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Pattern p = Pattern.compile("<dd class=\"fz24\">(.*?)</dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            ip = m.group(1);
        }
        return ip;
    }


    /**
     * @Author zyb
     * @Description 使用ip2region工具，根据ip定位到具体的位置
     * @Date 2020/7/19 11:28
     * @Param [ip]
     * @Return java.lang.String
     **/
    public static String getAddressByIp(String ip) {
        // 根据ip进行位置信息搜索
        DbConfig config = null;
        try {
            config = new DbConfig();
            // 获取ip库的位置（放在src下）（直接通过测试类获取文件Ip2RegionTest为测试类）
            String dbfile = IpUtils.class.getResource("/ip2region.db").getPath();
            DbSearcher searcher = new DbSearcher(config, dbfile);
            // 采用Btree搜索
            DataBlock block = searcher.btreeSearch(ip);
            String address = block.getRegion();
            // 打印位置信息（格式：国家|大区|省份|城市|运营商）
            System.out.println(address);
            return address;
        } catch (DbMakerConfigException | IOException e) {
            e.printStackTrace();
        }
        return "国家|大区|省份|城市|运营商";
    }


    /**
     * @Author zyb
     * @Description 获取到用户的ip地址
     * @Date 2020/7/19 11:29
     * @Param [request]
     * @Return java.lang.String
     **/
    public static String getIpAddr(HttpServletRequest request) {
        // 其实用户的ip地址你们都可以获取到:x-forwarded-for就是ip地址
        String ip = request.getHeader("x-forwarded-for");
        // 需要进行严谨判断(如果用户使用的有代理服务器(本地代理服务器，网络代理服务器都需要判断))
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                assert inet != null;
                ip = inet.getHostAddress();
            }
        }
        return ip;
    }
}
