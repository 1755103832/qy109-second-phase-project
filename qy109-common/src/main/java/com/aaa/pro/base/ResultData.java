package com.aaa.pro.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/8 14:49
 * @Description 通过接口返回值
 * 也就是说把后端的controller的返回值统一了
 * <p>
 * T:
 * 所谓的泛型说白了就相当于一个占位符
 **/
@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {

    private String code;

    private String message;

    private String detail;

    private T data;

}
