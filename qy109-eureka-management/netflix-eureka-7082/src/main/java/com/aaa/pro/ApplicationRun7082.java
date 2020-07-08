package com.aaa.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author zyb
 * @Date Create in 2020/7/8 9:49
 * @Description
 **/
@SpringBootApplication
@EnableEurekaServer
public class ApplicationRun7082 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun7082.class, args);
    }
}
