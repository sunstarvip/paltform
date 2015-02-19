package com.darknight.platform.account.permission.controller;

import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.service.PermissionService;
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

/**
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/permission")
public class PermissionController {
    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ModelAttribute("permission")
    public Permission getPermission(@RequestParam(value = "permissionId", required = false) String permissionId) {
        if (StringUtils.isNotEmpty(permissionId)) {
            return permissionService.find(permissionId);
        }
        Permission permission = new Permission();
        return permission;
    }

    @RequestMapping(value={"list"}, method={RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, @PageableDefault(10) Pageable pageable) {
        Page<Permission> permissionPage = permissionService.findAll(pageable);
        model.addAttribute("permissionPage", permissionPage);
        return "platform/permission/permissionList";
    }

    @RequestMapping(value={"add"}, method={RequestMethod.GET})
    public String add(HttpServletRequest request, Model model) {
        model.addAttribute("permission", new Permission());
        return "platform/permission/permissionEdit";
    }

    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(@ModelAttribute("permission") Permission permission, HttpServletRequest request, Model model) {
        permission = permissionService.save(permission);
        return "redirect:/platform/account/permission/list";
    }
}
