package com.aaa.pro.vo;

import com.aaa.pro.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author zyb
 * @Date Create in 2020/7/21 17:15
 * @Description
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class RoleVo implements Serializable {

    private List<Long> menuId;

    private Role role;

    private Integer pageNum;

    private Integer pageSize;

}
