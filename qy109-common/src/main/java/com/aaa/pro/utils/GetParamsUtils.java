package com.aaa.pro.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author zyb
 * @Date Create in 2020/7/19 11:15
 * @Description 获取以Get方式请求的参数
 **/
public class GetParamsUtils {

    private GetParamsUtils() {
    }

    /**
     * @Author zyb
     * @Description 获取参数的方法
     * @Date 2020/7/19 11:15
     * @Param [request]
     * @Return java.lang.String
     **/
    public static String getParams(HttpServletRequest request) {
        StringBuilder params = new StringBuilder("?");
        // 获取参数
        Enumeration<String> parameterNames = request.getParameterNames();
        // 判断确定用户使用的就是get方式
        if ("GET".equals(request.getMethod().toUpperCase())) {
            // 说明使用的get方式
            while (parameterNames.hasMoreElements()) {
                // 将参数封装进String中
                String name = parameterNames.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
        }
        if (params.length() > 0) {
            params.delete(params.length() - 1, params.length());
        }
        return params.toString();
    }
}
