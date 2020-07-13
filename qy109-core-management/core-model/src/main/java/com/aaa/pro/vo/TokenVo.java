package com.aaa.pro.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/13 10:29
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TokenVo implements Serializable {

    /**
     * 就是简单的token值(即uuid)
     */
    private String token;

    /**
     * 标识了方法是否执行成功
     */
    private Boolean ifSuccess;

    /**
     * 保存了token的key值
     */
    private String redisKey;
}
