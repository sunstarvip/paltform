package com.darknight.platform.account.permission.controller;

import com.darknight.core.util.JsonUtil;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DarKnight on 2014/5/26 0026.
 */
@RestController
@RequestMapping(value = "platform/account/permission")
public class PermissionController {
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

    @RequestMapping(value={"dataGrid"})
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

        Page<Permission> permissionPage = permissionService.findSearchPage(searchMap, pageRequest);

        String pageJson = JsonUtil.objToJsonString(permissionPage.getContent());
        return pageJson;
    }

    /**
     * 保存权限
     * @param permission 权限对象
     * @return
     */
    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(@ModelAttribute("permission") Permission permission) {
        //保存操作状态
        String status = "success";
        if(permission != null) {
            permission = permissionService.save(permission);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }

    /**
     * 更新权限
     * @param permission 权限对象
     * @return
     */
    @RequestMapping(value={"update"}, method={RequestMethod.POST})
    public String update(@ModelAttribute("permission") Permission permission) {
        //保存操作状态
        String status = "success";
        if(permission != null) {
            permission.setUpdateTime(new Date());
            permission = permissionService.save(permission);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }

    /**
     * 删除权限
     * 逻辑删除，若需物理删除建议使用SQL脚本
     * @param permissionId 权限ID
     * @return
     */
    @RequestMapping(value={"delete"}, method={RequestMethod.POST})
    public String delete(@RequestParam("id") String permissionId) {
        //保存操作状态
        String status = "success";
        if(StringUtils.isNotBlank(permissionId)) {
            permissionService.delete(permissionId);
        }else {
            status = "fail";
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("status", status);

        return JsonUtil.objToJsonString(resultMap);
    }
}
