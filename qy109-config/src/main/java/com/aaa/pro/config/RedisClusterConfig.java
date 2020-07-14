package com.aaa.pro.config;

import com.aaa.pro.properties.RedisClusterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**jkm
 * @Author
 * @Date Create in 2020/7/10 15:24
 * @Description
 **/
@Configuration
public class RedisClusterConfig {

    @Autowired
    private RedisClusterProperties redisClusterProperties;

    @Bean
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        // 1.先连接上远程redis服务器(ip地址和端口号)
        // 192.168.23.166:6380....
        String ipAddr = redisClusterProperties.getNodes();
        // 2.分割ipAddr，以","进行分割
        // ["192.168.23.166:6380","192.168.23.166:6381"...]
        String[] ipsAndPorts = ipAddr.split(",");
        // 3.循环获取每一台服务器的ip和端口号
        for (String ipAndPort : ipsAndPorts) {
            // 第一次循环(192.168.23.166:6380)
            // 第二次循环(192.168.23.166:6381)
            // 第三次循环(192.168.23.166:6382)
            // 4.再次分割，以":"为分割符
            //["192.168.23.166", "6380"](这个数组的长度是固定的)
            String[] split = ipAndPort.split(":");
            HostAndPort hostAndPort = new HostAndPort(split[0], Integer.parseInt(split[1]));
            hostAndPorts.add(hostAndPort);
        }
        // hostAndPortSet集合中已经有了这6台服务器的ip地址和端口号
        // 5.创建jedisCluster
        return new JedisCluster(hostAndPorts, redisClusterProperties.getCommandTimeout(), redisClusterProperties.getMaxAttempts());
    }
}
