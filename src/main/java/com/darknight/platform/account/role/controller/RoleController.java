package com.darknight.platform.account.role.controller;

import com.darknight.core.base.entity.DataGridEntity;
import com.darknight.core.util.JsonUtil;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.service.PermissionService;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 角色管理类
 * Created by DarKnight on 2014/5/26 0026.
 */
@RestController
@RequestMapping(value = "platform/account/role")
public class RoleController {
    private RoleService roleService;
    private PermissionService permissionService;

    @Resource
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Resource
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 通过角色ID查询对应角色对象
     * @param roleId 角色ID
     * @return Role 角色对象
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
     * 通过权限ID列表查询对应权限实体列表
     * @param permissionIdList 权限ID列表
     * @return List<Permission> 权限实体列表
     */
    @ModelAttribute("permissionList")
    public List<Permission> getRoleList(@RequestParam(value = "permissionList.id", required = false) List<String> permissionIdList) {
        List<Permission> permissionList = new ArrayList<>();
        if (permissionIdList != null && !permissionIdList.isEmpty()) {
            permissionList = permissionService.find(permissionIdList);
        }
        return permissionList;
    }

    /**
     * 查询数据表格
     * @param request
     * @return
     */
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

        // 生成分页数据
        Page<Role> rolePage = roleService.findSearchPage(searchMap, pageRequest);
        // 利用分页查询出来的数据生成数据表格，并JSON化
        DataGridEntity<Role> roleDataGrid = roleService.makeDataGrid(rolePage);

        String dataGridJson = JsonUtil.objToJsonString(roleDataGrid);
        return dataGridJson;
    }

    /**
     * 查询所有未逻辑删除的权限列表
     * 若角色ID不为空，则选中对应角色绑定的权限列表
     * @param roleId 角色ID，非必须
     * @return
     */
    @RequestMapping(value={"getPermissionList"})
    public String getRoleList(@RequestParam(value="roleId", required=false) String roleId) {
        List<Map<String, Object>> permissionMapList = new ArrayList<>();
        List<Permission> permissionList = new ArrayList<>();
        if(StringUtils.isNotBlank(roleId)) {
            // 查询对应角色是否已经绑定过权限
            permissionList = permissionService.findPermissionListByRoleId(roleId);
        }
        // 查询所有可用角色
        List<Permission> allPermissionList = permissionService.findAllOrderedVisible();

        if(allPermissionList != null && !allPermissionList.isEmpty()) {
            Map<String, Object> permissionMap = null;
            for(Permission permission : allPermissionList) {
                permissionMap = new HashMap<>();
                permissionMap.put("id", permission.getId());
                permissionMap.put("text", permission.getName());
                if(permissionList.contains(permission)) {
                    permissionMap.put("selected", true);
                }
                permissionMapList.add(permissionMap);
            }
        }

        return JsonUtil.objToJsonString(permissionMapList);
    }

    /**
     * 保存角色
     * @param role 角色对象
     * @return
     */
    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(Role role, @ModelAttribute("permissionList") List<Permission> permissionList) {
        //保存操作状态
        String status = "success";
        if(role != null) {
            // 保存角色绑定权限
            role.setPermissionList(permissionList);
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
    public String update(Role role, @ModelAttribute("permissionList") List<Permission> permissionList) {
        //保存操作状态
        String status = "success";
        if(role != null) {
            // 更新修改时间
            role.setUpdateTime(new Date());
            // 保存角色绑定权限
            role.setPermissionList(permissionList);
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
