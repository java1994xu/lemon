package com.lemon.system.role.service.impl;

import com.lemon.system.role.entity.Role;
import com.lemon.system.role.mapper.RoleMapper;
import com.lemon.system.role.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.system.user_role.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    private RoleMapper roleMapper;
    private UserRoleMapper userRoleMapper;

    @Override
    public void delete(String id) {
        //1、删除用户跟角色的关系
        userRoleMapper.deleteUserRoleRelation(id);
        //2、删除菜单权限跟角色的关系

        //3、最后删除角色。
        roleMapper.deleteById(id);

    }

    @Override
    public void deleteBatch(String ids) {
        //1、删除用户跟角色的关系
        userRoleMapper.deleteBatchUserRoleRelation(ids.split(";"));
        //2、删除菜单权限跟角色的关系

        //3、最后删除角色。
        roleMapper.deleteBatchIds(Arrays.asList(ids.split(";")));
    }


    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }
}
