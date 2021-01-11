package com.lemon.admin.menu.controller;


import com.lemon.common.vo.Result;
import com.lemon.admin.menu.entity.Menu;
import com.lemon.admin.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Slf4j
@RestController
@RequestMapping("/sys/menu")
public class MenuController {


    private MenuService menuService;


    @GetMapping("/getMenuList")
    public Result getMenuList(Menu menu, @RequestParam String userId) {
        List<Menu> menuList = menuService.getMenuList(menu, userId);

        return Result.success(menuList);
    }

    @PostMapping("/save")
    public Result save(Menu menu) {

        if (menuService.checkMenuExistsByName(menu) != null) {
            return Result.error("菜单已存在");
        } else {
            menuService.save(menu);
            return Result.success();
        }

    }


    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}

