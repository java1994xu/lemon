package com.lemon.system.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xubb
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "登录账号")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "md5密码盐")
    private String salt;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "性别(0-默认未知,1-男,2-女)")
    private Integer sex;

    @ApiModelProperty(value = "电子邮件")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "机构编码")
    private String orgCode;

    @ApiModelProperty(value = "状态0-正常,-1-冻结)")
    private Integer status;

    @ApiModelProperty(value = "删除状态(0-正常,1-已删除)")
    private Integer delFlag;

    @ApiModelProperty(value = "第三方登录的唯一标识")
    private String thirdId;

    @ApiModelProperty(value = "第三方类型")
    private String thirdType;

    @ApiModelProperty(value = "同步工作流引擎(1-同步,0-不同步)")
    private Integer activitiSync;

    @ApiModelProperty(value = "工号，唯一键")
    private String workNo;

    @ApiModelProperty(value = "职务，关联职务表")
    private String post;

    @ApiModelProperty(value = "座机号")
    private String telephone;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "身份（1普通成员 2上级）")
    private Integer userIdentity;

    @ApiModelProperty(value = "负责部门")
    private String departIds;

    @ApiModelProperty(value = "多租户标识")
    private String relTenantIds;


}
