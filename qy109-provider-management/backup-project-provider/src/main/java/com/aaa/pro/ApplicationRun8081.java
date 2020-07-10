package com.aaa.pro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author zyb
 * @Date Create in 2020/7/10 17:00
 * @Description 项目难点-->如果不在@SpringBootApplication注解上排除spring默认的redis自动装配，
 * springBoot在启动provider项目的时候会报错，由于springBoot约定优于配置的原因,控制台会报找不到redis6379端口号的错误
 * 为了能让spring boot去加载config项目中的redis配置，
 * 第一种解决方案就是在启动类中的@SpringBootApplication注解上排除spring默认的redis自动装配
 * springBoot约定优于配置-->
 *      springBoot看到项目中已经添加了redis的jar包，就会认为你会去用redis
 *      而且这个时候自己的项目中是没有自定义配置，那么就去加载默认的
 *      默认的的配置也没有，spring boot就会直接抛出异常
 *      service项目引入了config项目，provider项目引入了service项目，
 *      于是spring boot就会去依赖的所有的项目中去寻找redis的配置
 **/
@SpringBootApplication(exclude = {
        RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class
})
@MapperScan(basePackages = "com.aaa.pro.mapper")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ApplicationRun8081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8081.class, args);
    }
}
