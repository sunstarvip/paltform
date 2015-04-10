package com.darknight.platform.account.role.controller;

import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 角色管理类
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/role")
public class RolePageController {
    private RoleService roleService;

    @Resource
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 通过角色ID由Spring注入角色对象
     * @param roleId 角色ID
     * @return
     */
    @ModelAttribute("role")
    public Role getRole(@RequestParam(value = "roleId", required = false) String roleId) {
        if (StringUtils.isNotEmpty(roleId)) {
            return roleService.find(roleId);
        }
        Role role = new Role();
        return role;
    }

    /**
     * esayUI页面
     * @return
     */
    @RequestMapping(value={"esayuiPage"}, method={RequestMethod.GET})
    public String esayuiPage() {
        return "platform/role/role_easyui";
    }

    @RequestMapping(value={"dialogPage"}, method={RequestMethod.GET})
    public String dialogPage(@ModelAttribute("role") Role role, Model model) {
        model.addAttribute("role", role);
        return "platform/role/roleDialog_easyui";
    }
}
