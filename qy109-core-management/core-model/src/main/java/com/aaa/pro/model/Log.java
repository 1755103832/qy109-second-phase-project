package com.aaa.pro.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 17:10
 * @Description
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_log")
public class Log implements Serializable {

    /**
     * 日志ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作用户
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 耗时
     */
    @Column(name = "TIME")
    private Long time;

    /**
     * 操作者IP
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;

    /**
     * 操作地点
     */
    private String location;

    /**
     * 操作内容
     */
    @Column(name = "OPERATION")
    private String operation;

    /**
     * 操作方法
     */
    @Column(name = "METHOD")
    private String method;

    /**
     * 方法参数
     */
    @Column(name = "PARAMS")
    private String params;
}