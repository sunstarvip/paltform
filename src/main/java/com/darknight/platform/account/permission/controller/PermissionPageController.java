package com.darknight.platform.account.permission.controller;

import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by DarKnight on 2014/5/26 0026.
 */
@Controller
@RequestMapping(value = "platform/account/permission")
public class PermissionPageController {

    private PermissionService permissionService;

    @Resource
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 通过权限ID由Spring注入角色对象
     * @param permissionId 权限ID
     * @return
     */
    @ModelAttribute("permission")
    public Permission getPermission(@RequestParam(value = "permissionId", required = false) String permissionId) {
        if (StringUtils.isNotEmpty(permissionId)) {
            return permissionService.find(permissionId);
        }
        Permission permission = new Permission();
        return permission;
    }

    /**
     * esayUI页面
     * @return
     */
    @RequestMapping(value={"esayuiPage"}, method={RequestMethod.GET})
    public String esayuiPage() {
        return "platform/permission/permission_easyui";
    }

    @RequestMapping(value={"dialogPage"}, method={RequestMethod.GET})
    public String dialogPage(@ModelAttribute("permission") Permission permission, Model model) {
        model.addAttribute("permission", permission);
        return "platform/permission/permissionDialog_easyui";
    }
}
