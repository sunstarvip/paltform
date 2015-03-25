package com.darknight.platform.account.role.controller;

import com.darknight.core.util.JsonUtil;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

        Page<Role> rolePage = roleService.findSearchPage(searchMap, pageRequest);

        String pageJson = JsonUtil.objToJsonString(rolePage.getContent());
        return pageJson;
    }

    /**
     * 保存角色
     * @param role 角色对象
     * @return
     */
    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    @ResponseBody
    public String save(@ModelAttribute("role") Role role) {
        //保存操作状态
        String status = "success";
        if(role != null) {
            role = roleService.save(role);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }

    /**
     * 更新角色
     * @param role 角色对象
     * @return
     */
    @RequestMapping(value={"update"}, method={RequestMethod.POST})
    @ResponseBody
    public String update(@ModelAttribute("role") Role role) {
        //保存操作状态
        String status = "success";
        if(role != null) {
            role.setUpdateTime(new Date());
            role = roleService.save(role);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }

    /**
     * 删除角色
     * 逻辑删除，若需物理删除建议使用SQL脚本
     * @param roleId 角色ID
     * @return
     */
    @RequestMapping(value={"delete"}, method={RequestMethod.POST})
    @ResponseBody
    public String delete(@RequestParam("id") String roleId) {
        //保存操作状态
        String status = "success";
        if(StringUtils.isNotBlank(roleId)) {
            roleService.delete(roleId);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }

}
