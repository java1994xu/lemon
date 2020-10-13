package com.lemon.system.login.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xubb
 * @Description
 * @create 2020-09-15 16:59
 */
@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginDTO {

    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String captcha;
    @ApiModelProperty(value = "验证码key")
    private String checkKey;

}
