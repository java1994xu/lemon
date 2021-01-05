package com.lemon.system.user_role.mapper;

import com.lemon.system.user_role.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 删除单个角色id时，删除对应的用户角色关系
     * @param roleId
     * @return
     */
    boolean deleteUserRoleRelation(String roleId);

    /**
     * 删除多个角色时，批量删除对应的用户角色关系
     * @param roleIdArray
     * @return
     */
    boolean deleteBatchUserRoleRelation(String[] roleIdArray);
}
