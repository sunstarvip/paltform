package com.darknight.account.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.darknight.account.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DarKnight on 14-2-5.
 */
@Controller
@RequestMapping(value = "/platform/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = { "list" }, method = { RequestMethod.GET })
    public String list(HttpServletRequest request, Model model, @PageableDefaults(10) Pageable pageable) {
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        return "/platform/user/userList";
    }
}
