package com.darknight.platform.security.login.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
@RequestMapping(value = "/platform/security/login")
public class LoginController {
    private DefaultWebSecurityManager securityManager;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public void setSecurityManager(DefaultWebSecurityManager securityManager) {
        this.securityManager = securityManager;
    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login(Model model, HttpServletRequest request) {
//        String shiroLoginFailure = request.getParameter("shiroLoginFailure");
//        System.out.print("shiroLoginFailure: " + shiroLoginFailure);
//
//        return "platform/login/index";
//    }
//
//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String userLogin(Model model, HttpServletRequest request) {
//        String shiroLoginFailure = request.getParameter("shiroLoginFailure");
//        System.out.print("shiroLoginFailure: " + shiroLoginFailure);
//
//        return "platform/login/index";
//    }
//
//    @RequestMapping(value = "logout", method = RequestMethod.GET)
//    public String logout(Model model, HttpServletRequest request) {
//        System.out.print("logout sucess!");
//        return "redirect:/platform/account/user/list";
//    }
}
