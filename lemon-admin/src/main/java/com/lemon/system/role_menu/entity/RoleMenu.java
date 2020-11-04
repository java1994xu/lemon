package com.lemon.system.role_menu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_menu")
@ApiModel(value="RoleMenu对象", description="角色和菜单关联表")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private String roleId;

    @ApiModelProperty(value = "菜单ID")
    private String menuId;


}
