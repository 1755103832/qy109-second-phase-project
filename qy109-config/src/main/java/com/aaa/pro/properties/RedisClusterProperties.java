package com.aaa.pro.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/10 8:41
 * @Description
 * @ConfigurationProperties:-->默认只会从application.prorperties中去读取属性值
 **/
@Component
@PropertySource("classpath:properties/redis_cluster.properties")
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisClusterProperties implements Serializable {

    private String nodes;

    private Integer maxAttempts;

    private Integer commandTimeout;
}
