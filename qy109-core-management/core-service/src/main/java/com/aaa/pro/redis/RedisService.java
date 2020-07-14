package com.aaa.pro.redis;

import com.aaa.pro.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.aaa.pro.staticproperties.RedisProperties.*;


/**
 * @Author jkm
 * @Description redis的业务实现类
 * 如果需要存入redis数据库，首先应该把需要缓存的数据从mysql中查询出来,
 * 然后通过java代码存入到redis中
 * --->从mysql中所查询出来的可能就是对象类型，可能是List...
 * @Date 2020/5/14
 **/
@Service
public class RedisService<T> {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * spring提供的key的序列化器，作用把key进行序列化
     */
    private RedisSerializer redisSerializer = null;

    /**
     * @PostConstruct是框架的注解，通常在搭建架构和写工具类的时候用到 初始化序列器, keySerializer定义具体在什么时间初始化
     * 被@PostConstruct所标识的方法会在spring的配置加载完毕之后立即执行
     * 如果不加该注解， initRedisSerializer()有可能会在spring配置加载完毕之前去执行
     * 初始化redis的key序列化器
     */
    @PostConstruct
    public void initRedisSerializer() {
        if (this.redisSerializer == null) {
            this.redisSerializer = new JdkSerializationRedisSerializer(this.getClass().getClassLoader());
        }
    }

    /**
     * 向redis中保存数据
     * key:redis的key
     * value:所要保存的数据
     * redis中默认所实现的是覆盖
     * nxxx:
     * 只有两个固定值(架构规定，必须要传这两个固定值)
     * "nx":如果redis中没有这个key，才会去存，有这个key不再存数据
     * "xx":redis中有这个key才能存，没有key则不能存
     * 在实际开发中会有这种可能:
     * 当向redis存入的数据就必须要设置失效时间(秒杀，活动商品(xx小时之后价格恢复))
     * expx:
     * 只有两个固定值
     * ex:失效时间的单位为秒
     * px:失效时间的单位为毫秒
     * time:具体的失效时间
     * 硬编码是固定写死在程序员中的数据"",数字...
     * 最早学习数据源连接的时候--->class.forName("mysql...");
     *
     * @param key
     * @param value
     * @param nxxx
     * @param expx
     * @param time
     * @return
     */
    public String set(String key, T value, String nxxx, String expx, Integer time) {
        // 判断是否需要设置失效时间
        if (time != null && time > 0 &&
                (NX.equals(nxxx) || XX.equals(nxxx)) &&
                (EX.equals(expx) || PX.equals(expx))) {
            // 需要设置失效时间
            return jedisCluster.set(key, JsonUtils.toJsonString(value), nxxx, expx, time);
        } else {
            // 说明不需要设置失效时间
            // 需要再次判断--->是否nx或者xx
            if (NX.equals(nxxx)) {
                // getIntegerReply-->状态码(成功200，找不到400，报错500)--->如果找不到也可能会使程序受影响
                // 成功状态码:2xx 找不到状态码:4xx 异常状态码:5xx
                // 404--->可以对应多个异常(一对多的关系)
                // 为了保证返回给程序员的是一个真实有效的结果，所以返回的是受影响的行数
                return String.valueOf(jedisCluster.setnx(key, JsonUtils.toJsonString(value)));
                // 下面不能直接使用else，因为防止客户端传递过来的数据不是xx
                // 如果需要使用到其他工具类，直接从网上搜JSONUtil,StringUtil...
            } else if (XX.equals(nxxx)) {
                return jedisCluster.set(key, JsonUtils.toJsonString(value));
            }
        }
        return NO;
    }

    /**
     * 从redis中获取数据(单个对象)--->包含基本类型
     *
     * @param key
     * @return
     */
    public T getObject(String key) {
        String redisValue = jedisCluster.get(key);
        return (T) JsonUtils.toObject(redisValue, Object.class);
    }

    /**
     * @Author jkm
     * @Description 从redis中查询数据(单个数据)
     * @Date 2020/7/10 15:44
     * @Param [key]
     * @Return T
     **/
    public T getOne(String key) {
        return (T) JsonUtils.toObject(jedisCluster.get(key), Object.class);
    }

    /**
     * @Author jkm
     * @Description 从redis中查询数据(value值是字符串)
     * @Date 2020/7/10 15:43
     * @Param [key]
     * @Return java.lang.String
     **/
    public String getString(String key) {
        return jedisCluster.get(key);
    }

    /**
     * 从redis中获取数据(集合数据)
     *
     * @param key
     * @return
     */
    public List<T> getList(String key) {
        String redisValue = jedisCluster.get(key);
        return (List<T>) JsonUtils.toList(redisValue, Object.class);
    }

    /**
     * 架构的好不好，就直接意味着程序员开发是否方便
     * 也就是说程序员使用这套架构开发使用越方便，越简单，架构底层代码就绝对越复杂
     * 在实际开发中程序员有一个习惯:
     * 为了防止redis的key冲突导致数据覆盖，会把id作为redis的key去传
     * id有数字类型--->int/bigint--->自增(java代码中的属性对应的是int/long)
     * id有可能是uuid之后，其他的都必须要转换String
     * <p>
     * <p>
     * 思路:
     * 目前来说架构遇到的问题:
     * 封装redis的时候发现无法实现通用，因为JedisCluster只能接收String类型key值
     * 并不符合架构标准，最终可以把Object对象转换为字节数组来进行处理这个问题
     *
     * @param key
     * @return
     */
    public Long delOne(Object key) {
        return jedisCluster.del(object2ByteArray(key));
    }

    /**
     * 批量删除redis的数据
     * <p>
     * 这种循环的形式看似没有毛病，但是有问题
     * 假设有10个key需要删除
     * 其中九个都删了，但是只有一个没有删，如果这一个不是在最后
     * 那么结果返回一定大于0
     * 不能使用循环操作
     *
     * @param keys
     * @return
     */
    public Long delMany(Collection<T> keys) {
//        if (CollectionUtils.isEmpty(keys)) {
//            return 0L;
//        } else {
//            byte[][] keyBytes = this.collection2ByteArray(keys);
//            return jedisCluster.del(keyBytes);
//        }
        return CollectionUtils.isEmpty(keys) ? 0L : jedisCluster.del(this.collection2ByteArray(keys));
    }

    /**
     * 给缓存设置失效时间
     * 具体失效数据的key
     * expx：时间单位（两个固定值）
     * ex:时间单位是秒
     * px：时间单位是毫秒
     * time：具体失效的时间
     *
     * @param key
     * @param time
     * @return
     */
    public Long expire(String key, String expx, Integer time) {
        if (time == null || time == 0) {
            return 0L;
        }
        if (EX.equals(expx)) {
            return jedisCluster.expire(key, time);
        } else if (PX.equals(expx)) {
            Long millTime = Long.parseLong(String.valueOf(time));
            return jedisCluster.pexpire(key, millTime);
        }
        return 0L;
    }

    /**
     * 把Object对象类型转换为字节数组
     * <p>
     * <p>
     * 断言就是来判断用的:
     * 如果key有值则会去执行下面的代码
     * 如果key没有，则直接return了
     *
     * @param key
     * @return
     */
    private byte[] object2ByteArray(Object key) {
        Assert.notNull(key, "this key is required,you can't send null!");
        return this.redisSerializer == null && key instanceof byte[] ? (byte[]) key : this.redisSerializer.serialize(key);
    }

    /**
     * 把集合转换为二维字节数组
     *
     * @param keys
     * @return
     */
    private byte[][] collection2ByteArray(Collection<T> keys) {
        byte[][] bytes = new byte[keys.size()][];
        int i = 0;
        Object key;
        for (Iterator var4 = keys.iterator(); var4.hasNext(); bytes[i++] = object2ByteArray(key)) {
            key = var4.next();
        }
        return bytes;
    }
}
