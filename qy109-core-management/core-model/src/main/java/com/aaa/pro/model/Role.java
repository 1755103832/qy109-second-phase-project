package com.aaa.pro.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 17:06
 * @Description
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_role")
public class Role implements Serializable {

    /**
     * 角色ID
     */
    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "MODIFY_TIME")
    private String modifyTime;
}