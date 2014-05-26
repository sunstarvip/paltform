package com.darknight.platform.account.permission.service;

import com.darknight.platform.account.permission.entity.Permission;

import java.util.Set;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
public interface PermissionService {
    public Permission find(String permissionId);

    public Set<Permission> findPermissions(String accountName);

    public Set<String> findPermissionIds(Set<Permission> permissionSet);

    public Set<String> findPermissionIds(String accountName);
}
