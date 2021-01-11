package com.lemon.admin.user_role.service.impl;

import com.lemon.admin.user_role.entity.UserRole;
import com.lemon.admin.user_role.mapper.UserRoleMapper;
import com.lemon.admin.user_role.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
