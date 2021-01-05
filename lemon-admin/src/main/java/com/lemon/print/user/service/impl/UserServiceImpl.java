package com.lemon.print.user.service.impl;

import com.lemon.print.user.entity.User;
import com.lemon.print.user.mapper.UserMapper;
import com.lemon.print.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xubb
 * @since 2021-01-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
