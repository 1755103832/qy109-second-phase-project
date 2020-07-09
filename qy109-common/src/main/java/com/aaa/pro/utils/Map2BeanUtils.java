package com.aaa.pro.utils;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author project
 * @Date Create in 2020/7/9 14:53
 * @Description
 **/
public class Map2BeanUtils {

    /**
     * prevent to new this class
     */
    private Map2BeanUtils() {
    }

    /**
     * 高性能java实例化工具类
     */
    private static final Objenesis OBJENESIS = new ObjenesisStd(true);

    /**
     * 使用String效率太低，使用StringBuffer虽然效率提高了，但是相对于StringBuilder来说效率还是低
     */
    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    /**
     * 高性能反射工具类中，高性能反射字节集
     * ConcurrentHashMap:在线程中运转，这个Map会在当前线程中出现
     * 而且线程和线程具有隔离性，这里的Map就不会被其他的线程所干扰
     */
    private static final ConcurrentHashMap<Class, MethodAccess> CONCURRENT_HASH_MAP = new ConcurrentHashMap<>(16);

    /**
     * @Author project
     * @Description map转换java bean
     * @Date 2020/7/9 15:11
     * @Param [map, clazz]
     * @Return T
     **/
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
        // 1.获取实例对象信息
        T instance = OBJENESIS.newInstance(clazz);
        // 2.从Map中通过key(instance)，或者MethodAccess对象
        MethodAccess methodAccess = CONCURRENT_HASH_MAP.get(clazz);
        // 3.判断
        if (null == methodAccess) {
            // MethodAccess.get(User.class):
            // 获取的是User类中的MethodAccess的信息
            // 3.通过类获取MethodAccess对象
            methodAccess = MethodAccess.get(clazz);
            // 4.存入CONCURRENT_HASH_MAP中
            CONCURRENT_HASH_MAP.putIfAbsent(clazz, methodAccess);
        }
        // 5.循环Map对象
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            // Map中的数据需要通过对象的set方法来进行存放
            String setMethodName = setMethodName(entry.getKey());
            int index = methodAccess.getIndex(setMethodName, entry.getValue().getClass());
            methodAccess.invoke(instance, index, entry.getValue());
        }
        return instance;
    }

    /**
     * @Author project
     * @Description 通过字段拼接方法名
     * @Date 2020/7/9 15:14
     * @Param [fieldName]
     * @Return java.lang.String
     **/
    private static String setMethodName(String fieldName) {
        STRING_BUILDER.setLength(0);
        // setUsername();
        return STRING_BUILDER.append("set").append(firstWord2upperCase(fieldName)).toString();
    }

    /**
     * @Author project
     * @Description 把属性的首字母转换为大写
     * @Date 2020/7/9 15:15
     * @Param [str]
     * @Return java.lang.String
     **/
    private static String firstWord2upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
