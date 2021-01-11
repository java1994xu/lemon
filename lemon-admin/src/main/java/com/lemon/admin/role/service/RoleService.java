package com.lemon.admin.role.service;

import com.lemon.admin.role.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
public interface RoleService extends IService<Role> {

    /**
     * 删除单个角色
     * @param id
     */
    void delete(String id);

    /**
     * 批量删除角色
     * @param ids
     */
    void deleteBatch(String ids);
}
