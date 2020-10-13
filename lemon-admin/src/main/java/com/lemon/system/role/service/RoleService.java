package com.lemon.system.role.service;

import com.lemon.system.role.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xubb
 * @since 2020-09-11
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
