package com.darknight.platform.account.user.controller;

import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理类
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/user")
class UserPageController {
    private UserService userService;

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 通过用户ID由Spring注入用户对象
     * @param userId 用户ID
     * @return
     */
    @ModelAttribute("user")
    public User getUser(@RequestParam(value = "userId", required = false) String userId) {
        if (StringUtils.isNotBlank(userId)) {
            return userService.find(userId);
        }
        User user = new User();
        return user;
    }

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

    @RequestMapping(value={"dialogPage"}, method={RequestMethod.GET})
    public String dialogPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "platform/user/userDialog_easyui";
    }
}
