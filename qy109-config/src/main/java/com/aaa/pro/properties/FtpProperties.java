package com.aaa.pro.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/10 15:30
 * @Description
 **/
@Component
@PropertySource("classpath:properties/ftp.properties")
@ConfigurationProperties(prefix = "spring.ftp")
@Data
public class FtpProperties implements Serializable {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String basePath;

    private String httpPath;

}
