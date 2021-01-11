package com.lemon.admin.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.common.vo.Result;
import com.lemon.admin.login.entity.LoginDTO;
import com.lemon.admin.user.entity.User;
import com.lemon.admin.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xubb
 * @Description
 * @create 2020-09-11 1:23
 */
@RestController
@RequestMapping("/sys")
@Slf4j
@Api("用户登录")
public class LoginController {


    private UserService userService;


    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO dto) {
        Result result = new Result();
        String username = dto.getUsername();
        String password = dto.getPassword();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        User user = userService.getOne(queryWrapper);

        // TODO 校验验证码
        //1. 校验用户是否有效
        result = userService.checkUserIsEffective(user);
        if (!result.isSuccess()) {
            return result;
        }

        //2. 校验用户名或密码是否正确
      /*  String encryptPwd = PasswordUtil.encrypt(username, password, user.getSalt());
        String pwd = user.getPassword();
        if (!encryptPwd.equals(pwd)) {
            result.error500("用户名或密码错误");
            return result;
        }
*/
        return userService.getUserInfo(user);

    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
