package com.lemon.system.menu.service.impl;

import com.lemon.system.menu.entity.Menu;
import com.lemon.system.menu.mapper.MenuMapper;
import com.lemon.system.menu.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
