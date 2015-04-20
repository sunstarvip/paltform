package com.darknight.platform.account.permission.controller;

import com.darknight.core.base.entity.DataGridEntity;
import com.darknight.core.base.entity.ResultEntity;
import com.darknight.core.util.JsonUtil;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.entity.PermissionNode;
import com.darknight.platform.account.permission.service.PermissionService;
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
 * Created by DarKnight on 2014/5/26 0026.
 */
@RestController
@RequestMapping(value = "platform/account/permission")
public class PermissionController {
    /**
     * 日志操作对象
     */
    private final Logger logger = LoggerFactory.getLogger(PermissionController.class);
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

        // 生成分页数据
        Page<Permission> permissionPage = permissionService.findSearchPage(searchMap, pageRequest);
        // 利用分页查询出来的数据生成数据表格，并JSON化
        DataGridEntity<Permission> permissionDataGrid = permissionService.makeDataGrid(permissionPage);

        String dataGridJson = JsonUtil.objToJsonString(permissionDataGrid);
        return dataGridJson;
    }

    /**
     * 查询权限树
     * @param request
     * @return
     */
    @RequestMapping(value={"permissionTree"})
    public String permissionTree(HttpServletRequest request) {
        List<Permission> permissionList = permissionService.findAllVisibleTopPermission();
        List<PermissionNode> permissionNodeList = permissionService.makeNode(permissionList);

        String listJson = JsonUtil.objToJsonString(permissionNodeList);
        return listJson;
    }

    /**
     * 保存权限
     * @param permission 权限对象
     * @return
     */
    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(Permission permission) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        if(permission != null) {
            // 处理页面中可能为权限自动添加空对象作为父级权限，导致保存报错的BUG
            if(permission.getParent() != null && StringUtils.isBlank(permission.getParent().getId())) {
                permission.setParent(null);
            }
            permission = permissionService.save(permission);

            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("新增权限保存成功");
        }else {
            // 保存异常日志
            logger.info("PermissionController.save(Permission permission)异常");
            logger.info("其中permission为null");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("新增权限保存失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

    /**
     * 更新权限
     * @param permission 权限对象
     * @return
     */
    @RequestMapping(value={"update"}, method={RequestMethod.POST})
    public String update(Permission permission) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        if(permission != null) {
            // 处理页面中可能为权限自动添加空对象作为父级权限，导致保存报错的BUG
            if(permission.getParent() != null && StringUtils.isBlank(permission.getParent().getId())) {
                permission.setParent(null);
            }
            permission.setUpdateTime(new Date());
            permission = permissionService.save(permission);

            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("权限更新成功");
        }else {
            // 保存异常日志
            logger.info("PermissionController.update(Permission permission)异常");
            logger.info("其中permission为null");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("权限更新失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }

    /**
     * 删除权限
     * 逻辑删除，若需物理删除建议使用SQL脚本
     * @param permissionId 权限ID
     * @return
     */
    @RequestMapping(value={"delete"}, method={RequestMethod.POST})
    public String delete(@RequestParam("id") String permissionId) {
        //保存操作结果
        ResultEntity resultData = new ResultEntity();

        if(StringUtils.isNotBlank(permissionId)) {
            permissionService.delete(permissionId);

            // 修改操作状态为成功
            resultData.setStatus(ResultEntity.Status.SUCCESS);
            // 添加操作成功的返回信息
            resultData.setMsgInfo("权限删除成功");
        }else {
            // 保存异常日志
            logger.info("PermissionController.delete(String permissionId)异常");
            logger.info("其中permissionId为null或空白字符");
            // 添加操作错误的返回信息
            resultData.setMsgInfo("权限删除失败");
        }

        return JsonUtil.objToJsonString(resultData);
    }
}
