package com.lemon.admin.menu.service.impl;

import com.lemon.admin.menu.entity.Menu;
import com.lemon.admin.menu.mapper.MenuMapper;
import com.lemon.admin.menu.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList(Menu menu, String userId) {

        return menuMapper.getAllMenu(menu);
    }

    @Override
    public Menu checkMenuExistsByName(Menu menu) {
        List<Menu> menuList = menuMapper.checkMenuExistsByName(menu);
        if(menuList.size()>0){
            return menuList.get(0);
        }else{
            return null;
        }
    }


    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
}
