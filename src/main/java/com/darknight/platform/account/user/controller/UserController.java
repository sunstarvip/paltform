package com.darknight.platform.account.user.controller;

import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import com.darknight.platform.security.shiro.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public User getUser(@RequestParam(value = "userId", required = false) String userId) {
        if (StringUtils.isNotEmpty(userId)) {
            return userService.find(userId);
        }
        User user = new User();
        return user;
    }

    @RequestMapping(value={"list"}, method={RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, @PageableDefault(10) Pageable pageable) {
        Page<User> userPage = userService.findAll(pageable);
        model.addAttribute("userPage", userPage);
        return "platform/user/userList";
    }

    @RequestMapping(value={"add"}, method={RequestMethod.GET})
    public String add(HttpServletRequest request, Model model) {
        model.addAttribute("user", new User());
        return "platform/user/userAdd";
    }

    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(HttpServletRequest request, Model model) {
        User user = new User();
        String accountName = request.getParameter("accountName");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        user.setAccountName(accountName);
        user.setPassword(password);
        user.setName(name);
        //加密密码
        PasswordUtil.getPassword(user);
        userService.save(user);
        return "redirect:/platform/account/user/list";
    }
}
