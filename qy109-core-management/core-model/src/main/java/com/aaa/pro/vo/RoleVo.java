package com.aaa.pro.vo;

import com.aaa.pro.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author: jkm
 * @Date: 2020/05/27
 * @Time: 1:00
 * @Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoleVo implements Serializable {


    private List<Long> menuId;

    private Role role;

    private Integer pageNo;

    private Integer pageSize;

}