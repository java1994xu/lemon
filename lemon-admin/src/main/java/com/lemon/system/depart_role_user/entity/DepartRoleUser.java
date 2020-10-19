package com.lemon.system.depart_role_user.entity;

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
 * 部门角色用户表
 * </p>
 *
 * @author xubb
 * @since 2020-09-27
 */
@EqualsAndHashCode(callSuper = false)
@TableName("sys_depart_role_user")
@ApiModel(value="DepartRoleUser对象", description="部门角色用户表")
public class DepartRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色id")
    private String droleId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDroleId() {
        return droleId;
    }

    public void setDroleId(String droleId) {
        this.droleId = droleId;
    }

    @Override
    public String toString() {
        return "DepartRoleUser{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", droleId='" + droleId + '\'' +
                '}';
    }
}
