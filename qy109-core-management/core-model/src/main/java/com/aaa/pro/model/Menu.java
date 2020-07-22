package com.aaa.pro.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 16:47
 * @Description
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "t_menu")
public class Menu implements Serializable {

    /**
     * 菜单/按钮ID
     */
    @Id
    @Column(name = "MENU_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @Column(name = "PARENT_ID")
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 对应路由path
     */
    @Column(name = "PATH")
    private String path;

    /**
     * 对应路由组件component
     */
    @Column(name = "COMPONENT")
    private String component;

    /**
     * 权限标识
     */
    @Column(name = "PERMS")
    private String perms;

    /**
     * 图标
     */
    @Column(name = "ICON")
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 排序
     */
    @Column(name = "ORDER_NUM")
    private Double orderNum;

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

    /**
     * 子菜单(二级菜单)
     */
    private List<Menu> subMenu;
}