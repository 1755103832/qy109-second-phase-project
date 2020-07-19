package com.aaa.pro.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * @Author zyb
 * @Date Create in 2020/7/19 11:32
 * @Description post参数工具类
 **/
public class PostParamsUtils {


    private PostParamsUtils() {
    }


    /**
     * @Author zyb
     * @Description 获取参数的方法
     * @Date 2020/7/19 11:32
     * @Param [rcx]
     * @Return com.alibaba.fastjson.JSONObject
     **/
    public static JSONObject postParams(RequestContext rcx) {
        String body = null;
        if (!rcx.isChunkedRequestBody()) {
            ServletInputStream inp;
            try {
                inp = rcx.getRequest().getInputStream();
                if (null != inp) {
                    body = IOUtils.toString(inp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSON.parseObject(body);
    }
}
