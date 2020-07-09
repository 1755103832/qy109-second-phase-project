package com.aaa.pro.redis;

import com.aaa.pro.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.aaa.pro.staticproperties.RedisProperties.*;

/**
 * @Company: com.aaa.pro
 * @ProjectName: qy-109110-project
 * @PackageName: com.aaa.pro.redis
 * @Author： pro
 * @Date： Create in 17:29 2020/7/9
 * @description：
 */
@Service
public class RedisService<T> {
    @Autowired
    private JedisCluster jedisCluster;
    /**
     * spring提供的key的序列化器，作用把key进行序列化
     */
    private RedisSerializer keySerializer = null;
    /**
     * @Author: pro
     * @Description:
     *      向redis中保存数据
     * @Date: 17:53 2020/7/9
     * @param: [key, value, nxxx, expx, time]
     * @return java.lang.String
     */
public String set(String key, T value, String nxxx, String expx, Integer time){
    //判断是否需要设置失效时间
    if(null != time && 0< time &&
            (NX.equals(nxxx) || XX.equals(nxxx)) &&
            (EX.equals(expx) || PX.equals(expx))){
        //需要设置失效时间
        return jedisCluster.set(key, JSONUtil.toJsonString(value),nxxx,expx,time);
    }else {
        // 说明不需要设置失效时间
        // 需要再次判断---> 是否是nx或者xx
        if (NX.equals(nxxx)) {
           // getIntegerReply-->状态码(成功200，找不到400，报错500)--->如果找不到也可能会使程序受影响
           // 成功状态码:2xx 找不到状态码:4xx 异常状态码:5xx
           // 404--->可以对应多个异常(一对多的关系)
           // 为了保证返回给程序员的是一个真实有效的结果，所以返回的是受影响的行数
            return String.valueOf(jedisCluster.setnx(key,JSONUtil.toJsonString(value)));
            // 下面不能直接使用else，因为防止客户端传递过来的数据不是xx
            // 如果需要使用到其他工具类，直接从网上搜JSONUtil,StringUtil...
        } else if(XX.equals(nxxx)){
            return jedisCluster.set(key,JSONUtil.toJsonString(value));
        }
    }
    return NO;
}
    /**
     * @Author: pro
     * @Description:
     *          从redis中获取数据(单个对象)--->包含基本类型
     * @Date: 18:09 2020/7/9
     * @param: [key]
     * @return T
     */
    public T getObject(String key){
        String redisValue = jedisCluster.get(key);
        return (T)JSONUtil.toObject(redisValue, Object.class);
    }

    /**
     * @Author: pro
     * @Description:
     *          从redis中获取数据(集合数据)
     * @Date: 18:13 2020/7/9
     * @param: [key]
     * @return java.util.List<T>
     */
    public List<T> getList(String key){
        String redisValue = jedisCluster.get(key);
        return (List<T>) JSONUtil.toObject(redisValue, Object.class);
    }

    /**
     * @Author: pro
     * @Description:
     *      架构的好不好，就直接意味着程序员开发是否方便
     *      也就是说程序员使用这套架构开发使用越方便，越简单，架构底层代码就绝对越复杂
     *      在实际开发中程序员有一个习惯:
     *          为了防止redis的key冲突导致数据覆盖，会把id作为redis的key去传
     *          id有数字类型--->int/bigint--->自增(java代码中的属性对应的是int/long)
     *          id有可能是uuid之后，其他的都必须要转换String
     * @Date: 18:16 2020/7/9
     * @param: [key]
     * @return java.lang.Long
     */
    public Long delOne(Object key){
        // 1.把key转换成字节数据--->任何程序语言里面，字节是最通用的
        // 如果看不懂，你们可以用if判断，最好使用jdk8的新特性写(断言--->判断语言--->专门去做判断)
        // 断言的业务是需要自己去写的，我这借助的是spring提供的工具类，断言的用法就是把判断统一化
        // 断言需要咱们自己定义，实现所有的判断统一处理(把所有的判断封装到一个方法中用)

        /*byte[] k1;
                if(this.keySerializer == null && key instanceof byte[]) {
                    // 直接进行强转即可
                    k1 = (byte[]) key;
                } else {
                    // 使用序列化器进行序列化，相当于实现了序列化接口
                    k1 = this.keySerializer.serialize(key);
                }*/
        return jedisCluster.del(object2ByteArray(key));
    }

    public Long delMany(Collection<T> keys){
        // 1.严谨判断集合的长度，如果为0，就是逗比了
        if(CollectionUtils.isEmpty(keys)){
            return 0L;
        }else {
            // 因为JedisCluster中提供了可变长度的参数，所以咱们就可以使用这种模式来进行批量删除
            // 为了实现通用，还得必须转换成字节--->因为可变的，所以最好的方案就是使用二维数组来进行批量删除
            byte[][] keyBytes = this.collection2ByteArray(keys);
            return jedisCluster.del(keyBytes);
        }

    }

    /**
     * @author Seven Lee
     * @description
     *      把Object对象类型转换为字节数组
     * @param [key]
     * @date 2020/3/23
     * @return byte[]
     * @throws
     **/
    private byte[] object2ByteArray(Object key) {
        Assert.notNull(key,"this key is required, you can't send null!");// spring工具类--->如果为null直接抛异常，如果不为null直接往下走
        // 因为要转换字节数组，需要进行把对象序列化(让实体类必须要实现序列化接口的原因)
        return this.keySerializer == null && key instanceof byte[] ? (byte[]) key : this.keySerializer.serialize(key);
    }

    /**
     * @author Seven Lee
     * @description
     *      把集合转换为二维字节数组
     * @param [keys]
     * @date 2020/3/23
     * @return byte[][]
     * @throws
     **/
    private byte[][] collection2ByteArray(Collection<T> keys) {
        byte[][] bytes = new byte[keys.size()][];// 定义一个长度为集合长度的二维数组
        int i = 0;// 二维数组的下标，来进行存储数据用
        Object key;// 因为keys是一个集合，泛型对象是Object--->所以需要循环这个集合，把集合中的所有元素都要序列化
        // 使用迭代器去循环keys的集合
        /**
         * Iterator var4 = keys.iterator()--->因为Collection不一定是List，有可能是Map
         * for(Iterator it : keys.iterator()) {
         *      if(it.hasNext()) {
         *          key = it.next();
         *          把这个key进行序列化--->把Object对象序列化成字节数组
         *          obejct2ByteAarry(key);
         *      }
         * }
         */
        for (Iterator var4 = keys.iterator(); var4.hasNext(); bytes[i++] = object2ByteArray(key)) {
            key = var4.next();// 集合中的第一个元素
        }
        return bytes;
    }

}
