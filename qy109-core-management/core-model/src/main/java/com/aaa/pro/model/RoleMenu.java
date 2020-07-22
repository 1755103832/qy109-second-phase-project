package com.aaa.pro.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 17:07
 * @Description
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_role_menu")
public class RoleMenu implements Serializable {

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "MENU_ID")
    private Long menuId;
}