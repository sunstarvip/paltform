package com.darknight.platform.security.login.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
public class LoginController {
    private DefaultWebSecurityManager securityManager;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public void setSecurityManager(DefaultWebSecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    /**
     * 跳转至登录页面
     * 使用本地Controller来控制用户登录
     * @return
     */
    @RequestMapping(value = "loginPage", method = {RequestMethod.GET})
    public String loginPage() {
        return "platform/system/loginPage";
    }

    /**
     * 通过本地Controller来控制用户登录过程
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "webLogin", method = RequestMethod.POST)
    public String webLogin(Model model, HttpServletRequest request) {
        // 获取当前登录对象
        Subject loginUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                request.getParameter("username"),
                request.getParameter("password"));
        // 判断用户是否选择 记住我 选项
        if(StringUtils.equals("true", request.getParameter("rememberMe"))) {
            token.setRememberMe(true);
        }

        try {
            // 通过Shiro进行用户登录
            loginUser.login(token);
            return "redirect:/indexPage";
        }catch (UnknownAccountException e) {//登录失败
            e.printStackTrace();
            log.error("LoginController.webLogin()异常");
            log.error("用户名不存在");
            model.addAttribute("errorInfo", "用户名/密码错误");
        }catch (IncorrectCredentialsException e) {//登录失败
            e.printStackTrace();
            log.error("LoginController.webLogin()异常");
            log.error("用户密码错误");
            model.addAttribute("errorInfo", "用户名/密码错误");
        }catch (AuthenticationException e) {//登录失败
            e.printStackTrace();
            log.error("LoginController.webLogin()异常");
            log.error("其他错误");
            model.addAttribute("errorInfo", "用户名/密码错误");
        }

        return "platform/system/loginPage";
    }

    /**
     * Shiro登录控制，判断用户未登录时进入（GET）并跳转至登录页面，
     * 用户登录失败时进入（POST），重新转至登录页面，
     * 并提示用户登录失败原因
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(Model model, HttpServletRequest request) {
        String errorClassName = (String)request.getAttribute("shiroLoginFailure");
        if(UnknownAccountException.class.getName().equals(errorClassName)) {
            log.error("LoginController.login()异常");
            log.error("用户名不存在");
            model.addAttribute("errorInfo", "用户名/密码错误");
        }else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            log.error("LoginController.login()异常");
            log.error("用户密码错误");
            model.addAttribute("errorInfo", "用户名/密码错误");
        }else if(errorClassName != null) {
            log.error("LoginController.login()异常");
            log.error("其他错误");
            model.addAttribute("errorInfo", "未知错误：" + errorClassName);
        }
        return "platform/system/loginShiro";
    }

    /**
     * 用户首页
     * 自定义页面
     * @param request
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "platform/system/index";
    }

    /**
     * 用户首页
     * esayUI页面
     * @return
     */
    @RequestMapping(value={"indexPage"}, method={RequestMethod.GET})
    public String indexPage() {
        return "platform/system/index_easyui";
    }
}
