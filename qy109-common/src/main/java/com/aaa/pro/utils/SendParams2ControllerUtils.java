package com.aaa.pro.utils;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Author zyb
 * @Date Create in 2020/7/19 11:34
 * @Description 从路由发送数据到目标controller，跳转到controller的工具类
 **/
public class SendParams2ControllerUtils {

    private SendParams2ControllerUtils() {
    }

    /**
     * @Author zyb
     * @Description 发送数据的方法
     * @Date 2020/7/19 11:34
     * @Param [bodyJson, rcx, request]
     * @Return void
     **/
    public static void sendParams(JSONObject bodyJson, RequestContext rcx, HttpServletRequest request) {
        // 先将json转换成二进制流
        String body = bodyJson.toString();
        final byte[] bodyBytes = body.getBytes();
        // 把request对象放进上下文对象中
        rcx.setRequest(new HttpServletRequestWrapper(request) {

            @Override
            public ServletInputStream getInputStream() {
                return new ServletInputStreamWrapper(bodyBytes);
            }

            @Override
            public int getContentLength() {
                return bodyBytes.length;
            }

            @Override
            public long getContentLengthLong() {
                return bodyBytes.length;
            }
        });
    }
}
