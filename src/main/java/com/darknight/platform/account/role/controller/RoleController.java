package com.darknight.platform.account.role.controller;

import com.darknight.core.base.entity.DataGridEntity;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.core.util.JsonUtil;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.service.PermissionService;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    /**
     * 日志操作对象
     */
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
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
    public List<Permission> getPermissionList(@RequestParam(value = "permissionList.id", required = false) List<String> permissionIdList) {
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

    @RequestMapping(value={"getRoleList"})
    public String getRoleList(@RequestParam(value="userId", required=false) String userId) {
        List<Map<String, Object>> roleMapList = new ArrayList<>();
        List<Role> roleList = new ArrayList<>();
        if(StringUtils.isNotBlank(userId)) {
            // 查询对应用户是否已经绑定过角色
            roleList = roleService.findRoleListByUserId(userId);
        }
        // 查询所有可用角色
        List<Role> allRoleList = roleService.findAllOrderedVisible();

        if(allRoleList != null && !allRoleList.isEmpty()) {
            Map<String, Object> roleMap = null;
            for(Role role : allRoleList) {
                roleMap = new HashMap<>();
                roleMap.put("id", role.getId());
                roleMap.put("text", role.getName());
                if(roleList.contains(role)) {
                    roleMap.put("selected", true);
                }
                roleMapList.add(roleMap);
            }
        }

        return JsonUtil.objToJsonString(roleMapList);
    }

    /**
     * 保存角色
     * @param role 角色对象
     * @return
     */
    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(Role role, @ModelAttribute("permissionList") List<Permission> permissionList) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        if(role != null) {
            // 保存角色绑定权限
            role.setPermissionList(permissionList);
            role = roleService.save(role);

            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("新增角色保存成功");
        }else {
            // 保存异常日志
            logger.info("RoleController.save(Role role, List<Permission> permissionList)异常");
            logger.info("其中role为null");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("新增角色保存失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

    /**
     * 更新角色
     * @param role 角色对象
     * @return
     */
    @RequestMapping(value={"update"}, method={RequestMethod.POST})
    public String update(Role role, @ModelAttribute("permissionList") List<Permission> permissionList) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        if(role != null) {
            // 更新修改时间
            role.setUpdateTime(new Date());
            // 保存角色绑定权限
            role.setPermissionList(permissionList);
            role = roleService.save(role);

            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("角色更新成功");
        }else {
            // 保存异常日志
            logger.info("RoleController.update(Role role, List<Permission> permissionList)异常");
            logger.info("其中role为null");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("角色更新失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

    /**
     * 删除角色
     * 逻辑删除，若需物理删除建议使用SQL脚本
     * @param roleId 角色ID
     * @return
     */
    @RequestMapping(value={"delete"}, method={RequestMethod.POST})
    public String delete(@RequestParam("id") String roleId) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        if(StringUtils.isNotBlank(roleId)) {
            roleService.delete(roleId);

            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("角色删除成功");
        }else {
            // 保存异常日志
            logger.info("RoleController.delete(String roleId)异常");
            logger.info("其中roleId为null或空白字符");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("角色删除失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

}
