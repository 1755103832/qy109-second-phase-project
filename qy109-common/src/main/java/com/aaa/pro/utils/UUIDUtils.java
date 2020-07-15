package com.aaa.pro.utils;

import java.util.UUID;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 10:07
 * @Description
 **/
public class UUIDUtils {

    private UUIDUtils() {
    }

    /**
     * @Author zyb
     * @Description 随机一个uuid
     * @Date 2020/7/13 10:12
     * @Param []
     * @Return java.lang.String
     **/
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
