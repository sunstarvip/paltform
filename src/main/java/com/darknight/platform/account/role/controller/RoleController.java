package com.darknight.platform.account.role.controller;

import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/role")
public class RoleController {
    private RoleService roleService;

    @Resource
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @ModelAttribute("role")
    public Role getRole(@RequestParam(value = "roleId", required = false) String roleId) {
        if (StringUtils.isNotEmpty(roleId)) {
            return roleService.find(roleId);
        }
        Role role = new Role();
        return role;
    }

    @RequestMapping("list")
    public String list(HttpServletRequest request, Model model, @PageableDefault(10) Pageable pageable) {
        Page<Role> rolePage = roleService.findAll(pageable);
        model.addAttribute("rolePage", rolePage);
        return "platform/role/roleList";
    }

    @RequestMapping(value={"add"}, method={RequestMethod.GET})
    public String add(HttpServletRequest request, Model model) {
        model.addAttribute("role", new Role());
        return "platform/role/roleEdit";
    }

    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(@ModelAttribute("role") Role role, HttpServletRequest request, Model model) {
        role = roleService.save(role);
        return "redirect:/platform/account/role/list";
    }
}
