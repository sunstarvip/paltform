package com.darknight.platform.system.menu.controller;

import com.darknight.platform.system.menu.entity.Menu;
import com.darknight.platform.system.menu.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 菜单实体控制层
 * Created by DarKnight on 2015/4/1.
 */
@Controller
@RequestMapping(value = "platform/system/menu")
public class MenuPageController {
    private MenuService menuService;

    @Resource
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 通过系统菜单ID由Spring注入系统菜单对象
     * @param menuId 系统菜单ID
     * @return
     */
    @ModelAttribute("menu")
    public Menu getMenu(@RequestParam(value = "menuId", required = false) String menuId) {
        if (StringUtils.isNotBlank(menuId)) {
            return menuService.find(menuId);
        }
        Menu menu = new Menu();
        return menu;
    }

    /**
     * esayUI页面
     * @return
     */
    @RequestMapping(value={"esayuiPage"}, method={RequestMethod.GET})
    public String esayuiPage() {
        return "platform/menu/menu_easyui_old";
    }

    @RequestMapping(value={"dialogPage"}, method={RequestMethod.GET})
    public String dialogPage(@ModelAttribute("menu") Menu menu, Model model) {
        model.addAttribute("menu", menu);
        return "platform/menu/menuDialog_easyui";
    }
}
