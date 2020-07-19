package com.aaa.pro.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author zyb
 * @Date Create in 2020/7/19 10:50
 * @Description
 **/
@Slf4j
public class BaseUtils {

    private BaseUtils() {
    }

    /**
     * @Author zyb
     * @Description 判断是否为空
     * @Date 2020/7/19 10:50
     * @Param [str]
     * @Return boolean
     **/
    public static boolean checkIsNotNull(Object str) {
        return str != null && str != "";
    }

    /**
     * @Author zyb
     * @Description 将对象转换成字符串
     * @Date 2020/7/19 10:52
     * @Param [obj]
     * @Return java.lang.String
     **/
    public static String trans2String(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return "";
    }

    /**
     * @Author zyb
     * @Description 将对象转换成数字
     * @Date 2020/7/19 10:52
     * @Param [obj]
     * @Return java.lang.Integer
     **/
    public static Integer trans2Int(Object obj) {
        if (obj != null && obj != "") {
            return Integer.parseInt(obj.toString());
        }
        return null;
    }

    /**
     * @Author zyb
     * @Description 获取当前时间yyyy-MM-dd
     * @Date 2020/7/19 10:52
     * @Param []
     * @Return java.util.Date
     **/
    public static Date getNowDate() {
        // 设置格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        try {
            time = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            log.error(e.getMessage() + "获取当前时间出现异常");
            e.printStackTrace();
        }
        return time;
    }

    /**
     * @Author zyb
     * @Description 获取当前时间yyyy-MM-dd HH:mm:ss
     * @Date 2020/7/19 10:53
     * @Param []
     * @Return java.util.Date
     **/
    public static Date getNowDateTime() {
        // 设置格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            log.error(e.getMessage() + "获取当前时间出现异常");
            e.printStackTrace();
        }
        return time;
    }


    /**
     * @Author zyb
     * @Description 获取未来 第 past 天的日期
     * @Date 2020/7/19 10:54
     * @Param [past]
     * @Return java.util.Date
     **/
    public static Date getFutureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = sdf.parse(sdf.format(today));
        } catch (ParseException e) {
            log.error(e.getMessage() + "获取几天后的时间出现异常");
            e.printStackTrace();
        }
        return time;
    }


    /**
     * @Author zyb
     * @Description 日期转换成字符串
     * @Date 2020/7/19 10:55
     * @Param [date]
     * @Return java.lang.String
     **/
    public static String date2Str(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * @Author zyb
     * @Description 字符串转换成日期 
     * @Date 2020/7/19 10:56
     * @Param [str]
     * @Return java.util.Date
     **/
    public static Date str2Date(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}