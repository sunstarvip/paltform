package com.darknight.platform.account.permission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/permission")
public class PermissionPageController {

    /**
     * esayUI页面
     * @return
     */
    @RequestMapping(value={"esayuiPage"}, method={RequestMethod.GET})
    public String esayuiPage() {
        return "platform/permission/permission_easyui";
    }

}
