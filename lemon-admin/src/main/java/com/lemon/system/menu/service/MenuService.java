package com.lemon.system.menu.service;

import com.lemon.system.menu.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
public interface MenuService extends IService<Menu> {


    /**
     * 通过userId+查询条件  查询该用户的菜单列表
     *
     * @param userId
     * @return
     */
    List<Menu> getMenuList(Menu menu, String userId);


    /**
     * 通过菜单名跟parentId，查询菜单是否存在，存在返回菜单，不存在返回null
     * @param menu
     * @return
     */
    Menu checkMenuExistsByName(Menu menu);
}
