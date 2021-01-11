package com.lemon.admin.menu.mapper;

import com.lemon.admin.menu.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getAllMenu(Menu menu);

    List<Menu> getMenuList(Menu menu,String userId);

    List<Menu> checkMenuExistsByName(Menu menu);
}
