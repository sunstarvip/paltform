package com.darknight.platform.account.user.controller;

import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "/platform/account/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User getUser(@RequestParam(value = "userId", required = false) String roleId) {
        if (StringUtils.isNotEmpty(roleId)) {
            return userService.find(roleId);
        }
        User user = new User();
        return user;
    }

    @ModelAttribute("list")
    public String list(HttpServletRequest request, Model model, @PageableDefault(10) Pageable pageable) {
        System.out.println("11111111111111111111111111111111111111111111");
        return "platform/user/userList";
    }
}
