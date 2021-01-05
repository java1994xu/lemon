package com.lemon.system.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.constant.CommonConstants;
import com.lemon.common.vo.Result;
import com.lemon.system.user.entity.User;
import com.lemon.system.user.mapper.UserMapper;
import com.lemon.system.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.system.user_role.entity.UserRole;
import com.lemon.system.user_role.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserMapper userMapper;
    private UserRoleMapper userRoleMapper;


    /**
     * 校验用户是否有效
     *
     * @param user
     * @return
     */
    @Override
    public Result checkUserIsEffective(User user) {
        Result result = new Result();
        //情况1：根据用户信息查询，该用户不存在
        if (user == null) {
            return result.error500("该用户不存在，请注册");
        }
        //情况2：根据用户信息查询，该用户已注销
        if (CommonConstants.DEL_FLAG_Y.equals(user.getDelFlag())) {
            result.error500("该用户已注销");
            return result;
        }
        //情况3：根据用户信息查询，该用户已冻结
        if (CommonConstants.STATUS_DISABLE.equals(user.getStatus())) {
            result.error500("该用户已冻结");
            return result;
        }
        return result;
    }


    @Override
    public Result<JSONObject> getUserInfo(User user) {
        Result result = new Result();
        String password = user.getPassword();
        String username = user.getUserName();
        Map<String, Object> map = new HashMap<>();
        // 生成token
        map.put("token", "token");
        // 设置token缓存有效时间
        // 获取用户部门信息
        map.put("departs", "");
        map.put("roles", "[admin]");
        map.put("userInfo", user);
        result.setResult(map);
        result.setMessage("登录成功");
        return result;
    }





    @Override
    public IPage<User> getUserList(Page<User> userPage, User user) {
        return userMapper.getUserList(userPage,user);
    }

    @Override
    public void saveUserRoleRelation(User user, String roles) {
        if(StrUtil.isNotBlank(roles)){
            String[] array = roles.split(",");
            for (String roleId : array) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getUserId());
                userRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public void updateUserRoleRelation(User user, String roles) {

    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }
}

