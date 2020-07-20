package com.aaa.pro.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/20 21:26
 * @Description
 **/
@Component
@ConfigurationProperties(prefix = "spring.es")
@Data
@Accessors(chain = true)
public class EsProperties implements Serializable {

    private String ipAddr;

    private Integer port;

    private String clusterName;

    private String nodeName;

    private Integer pool;

}
