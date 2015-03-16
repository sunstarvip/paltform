package com.darknight.platform.account.role.controller;

import com.darknight.core.util.JsonUtil;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import com.darknight.platform.account.user.entity.User;
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
 * 角色管理类
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

    @RequestMapping(value={"dataGrid"}, method={RequestMethod.POST})
    @ResponseBody
    public String dataGrid(HttpServletRequest request) {
        //由于easyUI默认页码从1开始, 分页查询时需要相应处理
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        //获取查询条件
        String name = request.getParameter("name");
        //封装查询条件
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("like_name", name);

        PageRequest pageRequest = null;
        if(StringUtils.isNumeric(pageStr) && StringUtils.isNumeric(rowsStr)) {
            Integer page = Integer.valueOf(pageStr);
            Integer rows = Integer.valueOf(rowsStr);
            pageRequest = new PageRequest(page-1, rows);
        }else {
            pageRequest = new PageRequest(0, 10);
        }

        Page<User> userPage = roleService.findSearchPage(searchMap, pageRequest);

        String userPageJson = JsonUtil.objToJsonString(userPage.getContent());
        return userPageJson;
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
