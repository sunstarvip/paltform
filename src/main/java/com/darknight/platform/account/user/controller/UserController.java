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
import java.util.HashMap;
import java.util.Map;

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
     * esayUI列表页面
     * @param request
     * @return
     */
    @RequestMapping(value={"esayuiPage"}, method={RequestMethod.GET})
    public String esayuiPage(HttpServletRequest request) {
//        return "platform/user/userList_easyui";
        return "platform/user/userAdd_easyui";
    }

    /**
     * 分页查询用户列表数据
     * @param request
     * @param pageable 分页容器
     * @return
     */
    @RequestMapping(value={"dataGrid"}, method={RequestMethod.POST})
    @ResponseBody
    public String dataGrid(HttpServletRequest request, @PageableDefault(10) Pageable pageable) {
        //由于easyUI默认页码从1开始, 分页查询时需要相应处理
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        //获取查询条件
        String accountName = request.getParameter("accountName");
        String phoneNum = request.getParameter("phoneNum");
        //封装查询条件
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("like_accountName", accountName);
        searchMap.put("phoneNum", phoneNum);

        PageRequest pageRequest = null;
        if(StringUtils.isNumeric(pageStr) && StringUtils.isNumeric(rowsStr)) {
            Integer page = Integer.valueOf(pageStr);
            Integer rows = Integer.valueOf(rowsStr);
            pageRequest = new PageRequest(page-1, rows);
        }else {
            pageRequest = new PageRequest(0, 10);
        }

//        Page<User> userPage = userService.findAll(pageRequest);
        Page<User> userPage = userService.findSearchPage(searchMap, pageRequest);

        String userPageJson = JsonUtil.objToJsonString(userPage.getContent());
//        System.out.println("JSON: " + userPageJson);
        return userPageJson;
    }

    /**
     * 新增用户
     * @param model
     * @return
     */
    @RequestMapping(value={"add"}, method={RequestMethod.GET})
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "platform/user/userAdd";
    }

    /**
     * 编辑用户
     * @param userId 用户ID
     * @param model
     * @return
     */
    @RequestMapping(value={"edit"}, method={RequestMethod.GET})
    public String edit(@RequestParam(value = "userId", required = false) String userId, Model model) {
        if(StringUtils.isNotBlank(userId)) {
            User user = userService.find(userId);
            model.addAttribute("user", user);
        }

        return "platform/user/userEdit";
    }

    /**
     * 保存用户
     * @param user 用户对象
     * @return
     */
    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    @ResponseBody
    public String save(@ModelAttribute("user") User user) {
        //保存操作状态
        String status = "sucess";
        //加密密码
        if(user != null) {
            ShiroPasswordUtil.getPassword(user);
            user = userService.save(user);
        }else {
            status = "fail";
        }

        return status;
    }

    /**
     * 更新用户
     * @param user 用户对象
     * @return
     */
    @RequestMapping(value={"update"}, method={RequestMethod.POST})
    @ResponseBody
    public String update(@ModelAttribute("user") User user) {
        //保存操作状态
        String status = "sucess";
        //加密密码
        if(user != null) {
            user = userService.save(user);
        }else {
            status = "fail";
        }

        return status;
    }

    /**
     * 删除用户
     * 逻辑删除，若需物理删除建议使用SQL脚本
     * @param userId 用户ID
     * @return
     */
    @RequestMapping(value={"delete"}, method={RequestMethod.POST})
    @ResponseBody
    public String delete(@RequestParam("userId") String userId) {
        //保存操作状态
        String status = "sucess";
        //加密密码
        if(StringUtils.isNotBlank(userId)) {
            userService.delete(userId);
        }else {
            status = "fail";
        }

        return status;
    }
}
