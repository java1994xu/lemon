package com.lemon.admin.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.constant.CommonConstants;
import com.lemon.common.utils.StringUtils;
import com.lemon.common.vo.Result;
import com.lemon.admin.user.entity.User;
import com.lemon.admin.user.service.UserService;
import com.lemon.utils.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Slf4j
@Api(tags = "用户")
@RestController
@RequestMapping("sys/user")
public class UserController {

    private UserService userService;


    @GetMapping("/getUserLoginInfo")
    public Result getUserLoginInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.success(map);
    }


    @ApiOperation("获取用户列表")
    @GetMapping(value = "/getUserList")
    public Result<IPage<User>> getUserList(User user, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Result<IPage<User>> result = new Result<IPage<User>>();
        Page<User> page = new Page<User>(pageNo, pageSize);
        IPage<User> pageList = userService.getUserList(page, user);
        result.success200(pageList);
        return result;
    }


    @ApiOperation("添加用户")
    @PostMapping(value = "/save")
    public Result<User> save(@RequestBody User user) {
        Result<User> result = new Result<User>();
        try {

            String salt = StringUtils.randomGen(8);
            user.setSalt(salt);
            String passwordEncode = PasswordUtil.encrypt(user.getUserName(), user.getPassword(), salt);
            user.setPassword(passwordEncode);
            user.setStatus(CommonConstants.STATUS_ENABLE);
            user.setDelFlag(CommonConstants.DEL_FLAG_N);
            userService.save(user);
//            userService.addUserWithRole(user, selectedRoles);
//            userService.addUserWithDepart(user, selectedDeparts);
            result.success200(user, "添加成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }


    @ApiOperation("修改用户")
    @PutMapping(value = "/update")
    public Result<User> update(@RequestBody User user) {
        Result<User> result = new Result<>();
        try {
            if (userService.getById(user.getUserId()) == null) {
                result.error500("未找到对应用户");
            } else {

                userService.updateById(user);
                result.success200(user, "修改成功!");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        userService.removeById(id);
        return Result.success("删除用户成功");
    }

    /**
     * 批量删除用户
     */
    @ApiOperation("批量删除用户")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        userService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.success("批量删除用户成功");
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
