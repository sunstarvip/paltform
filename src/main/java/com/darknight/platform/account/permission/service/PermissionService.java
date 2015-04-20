package com.darknight.platform.account.permission.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.entity.PermissionNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
public interface PermissionService extends BaseService<Permission, String> {

    /**
     * 根据角色ID, 查询该角色对应的权限对象列表
     * @param roleId 角色ID
     * @return List<Permission> 权限对象列表
     */
    List<Permission> findPermissionListByRoleId(String roleId);

    Set<Permission> findPermissions(String accountName);

    Set<String> findPermissionIds(Set<Permission> permissionSet);

    Set<String> findPermissionIds(String accountName);

    /**
     * 通过条件分页查询未逻辑删除的权限列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    Page<Permission> findSearchPage(Map<String, Object> searchMap, Pageable page);

    /**
     * 查询所有未逻辑删除的顶级权限列表
     * @return
     */
    List<Permission> findAllVisibleTopPermission();

    /**
     * 根据当前权限实体查询所有未逻辑删除的子级权限列表
     * @param permission 权限实体
     * @return
     */
    List<Permission> findVisibleChildren(Permission permission);

    /**
     * 通过权限对象生成对应树型对象
     * @param permission 权限对象
     * @return
     */
    PermissionNode makeNode(Permission permission);

    /**
     * 通过权限对象列表生成对应树型对象列表
     * @param permissionList 权限对象列表
     * @return
     */
    List<PermissionNode> makeNode(List<Permission> permissionList);
}
