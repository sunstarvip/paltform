package com.darknight.platform.account.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理类
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/user")
class UserPageController {

    /**
     * 列表页面
     * @param request
     * @return
     */
    @RequestMapping(value={"list"}, method={RequestMethod.GET})
    public String list(HttpServletRequest request) {
        return "platform/user/userList";
    }

    /**
     * esayUI页面
     * @param request
     * @return
     */
    @RequestMapping(value={"esayuiPage"}, method={RequestMethod.GET})
    public String esayuiPage(HttpServletRequest request) {
        return "platform/user/user_easyui";
    }

}
