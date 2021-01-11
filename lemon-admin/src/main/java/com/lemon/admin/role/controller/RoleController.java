package com.lemon.admin.role.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.vo.Result;
import com.lemon.admin.role.entity.Role;
import com.lemon.admin.role.service.RoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;


    @ApiOperation("角色分页查询")
    @GetMapping("/getRoleList")
    public Result<IPage<Role>> getRoleList(String roleName, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Role> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        IPage<Role> roleIPage = roleService.page(page, queryWrapper);
        return Result.success(roleIPage, "查询成功");

    }

    @ApiOperation("获取所有的角色")
    @GetMapping("/getAllRoles")
    public Result<List<Role>> getAllRoles() {
        Result<List<Role>> result = new Result<>();
        List<Role> roleList = roleService.list();
        if (roleList == null) {
            result.error500("未找到角色信息");
        } else {
            result.success200(roleList, "查询成功");

        }
        return result;
    }


    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result<Role> save(@RequestBody Role role) {
        Result<Role> result = new Result<Role>();
        try {
            roleService.save(role);
            result.success200(role, "添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }


    @ApiOperation("修改角色")
    @PutMapping("/update")
    public Result update(@RequestBody Role role) {
        if (roleService.getById(role.getRoleId()) == null) {
            return Result.error("未找到对应角色");
        } else {
            if (roleService.updateById(role)) {
                return Result.success("修改成功");
            } else {
                return Result.error("修改失败");

            }
        }

    }

    @ApiOperation("删除单个角色")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        try {
            roleService.delete(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("操作异常！");
        }
    }


    @ApiOperation("批量删除角色")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam String ids) {
        try {
            roleService.deleteBatch(ids);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("操作异常！");
        }
    }


    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

}
