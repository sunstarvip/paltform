package com.darknight.platform.system.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 菜单实体控制层
 * Created by DarKnight on 2015/4/1.
 */
@Controller
@RequestMapping(value = "platform/system/menu")
public class MenuPageController {

    /**
     * esayUI页面
     * @return
     */
    @RequestMapping(value={"esayuiPage"}, method={RequestMethod.GET})
    public String esayuiPage() {
        return "platform/menu/menu_easyui";
    }

}
