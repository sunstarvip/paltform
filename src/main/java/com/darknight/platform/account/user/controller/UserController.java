package com.darknight.platform.account.user.controller;

import com.darknight.core.util.JsonUtil;
import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import com.darknight.platform.security.shiro.util.ShiroPasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/user")
class UserController {
    private static final long serialVersionUID = -454150993895608956L;
    private UserService userService;

    @Resource
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

    @RequestMapping(value={"listPage"}, method={RequestMethod.GET})
    public String listPage(HttpServletRequest request) {

        return "platform/user/userList";
    }

    @RequestMapping(value={"dataGrid"}, method={RequestMethod.POST})
    @ResponseBody
    public String dataGrid(HttpServletRequest request, @PageableDefault(10) Pageable pageable) {
        //由于easyUI默认页码从1开始, 分页查询时需要相应处理
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");

        PageRequest pageRequest = null;
        if(StringUtils.isNumeric(pageStr) && StringUtils.isNumeric(rowsStr)) {
            Integer page = Integer.valueOf(pageStr);
            Integer rows = Integer.valueOf(rowsStr);
            pageRequest = new PageRequest(page-1, rows);
        }else {
            pageRequest = new PageRequest(0, 10);
        }

        Page<User> userPage = userService.findAll(pageRequest);
        String userPageJson = JsonUtil.objToJsonString(userPage.getContent());
        System.out.println("JSON: " + userPageJson);
        return userPageJson;
    }

    @RequestMapping(value={"add"}, method={RequestMethod.GET})
    public String add(HttpServletRequest request, Model model) {
        model.addAttribute("user", new User());
        return "platform/user/userEdit";
    }

    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(@ModelAttribute("user") User user, HttpServletRequest request, Model model) {
//        User user = new User();
//        String accountName = request.getParameter("accountName");
//        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        user.setAccountName(accountName);
//        user.setPassword(password);
//        user.setName(name);
        //加密密码
        ShiroPasswordUtil.getPassword(user);
        user = userService.save(user);
        return "redirect:/platform/account/user/list";
    }
}
