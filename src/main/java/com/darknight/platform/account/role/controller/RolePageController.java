package com.darknight.platform.account.role.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 角色管理类
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/role")
public class RolePageController {

    /**
     * esayUI页面
     * @return
     */
    @RequestMapping(value={"esayuiPage"}, method={RequestMethod.GET})
    public String esayuiPage() {
        return "platform/role/role_easyui";
    }

}
