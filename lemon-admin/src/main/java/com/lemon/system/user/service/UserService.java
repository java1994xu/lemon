package com.lemon.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.vo.Result;
import com.lemon.system.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
public interface UserService extends IService<User> {
    /**
     * 检查用户是否有效
     * @param user
     * @return
     */
    Result checkUserIsEffective(User user);

    /**
     * 获取用户登录时的信息
     * @param user
     * @return
     */
    Result getUserInfo(User user);


    /**
     * 根据传入的user对象，条件查询user列表
     *
     * @param userPage
     * @param user
     * @return
     */
    IPage<User> getUserList(Page<User> userPage, User user);

    /**
     * 添加用户时，同时添加绑定的角色关系
     * @param user
     * @param roles
     */
    void saveUserRoleRelation(User user, String roles);

    /**
     * 修改用户时，修改绑定的用户角色关系
     * @param user
     * @param roles
     */
    void updateUserRoleRelation(User user,String roles);
}
