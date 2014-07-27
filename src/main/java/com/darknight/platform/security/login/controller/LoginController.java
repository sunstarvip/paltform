package com.darknight.platform.security.login.controller;

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

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        System.out.print("username: " + username);
        String password = request.getParameter("password");
        System.out.print("password: " + password);
        String rememberMe = request.getParameter("rememberMe");
        System.out.print("rememberMe: " + rememberMe);
        return "platform/login/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String userLogin(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        System.out.print("username: " + username);
        String password = request.getParameter("password");
        System.out.print("password: " + password);
        String rememberMe = request.getParameter("rememberMe");
        System.out.print("rememberMe: " + rememberMe);
        return "platform/login/login";
    }
}
