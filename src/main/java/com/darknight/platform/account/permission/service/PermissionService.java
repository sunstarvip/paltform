package com.darknight.platform.account.permission.service;

import com.darknight.platform.account.permission.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
public interface PermissionService {
    public Permission find(String permissionId);

    public List<Permission> findAll();

    public Page<Permission> findAll(Pageable page);

    public void flush();

    public Permission save(Permission permission);

    public List<Permission> save(List<Permission> permissionList);

    public Set<Permission> findPermissions(String accountName);

    public Set<String> findPermissionIds(Set<Permission> permissionSet);

    public Set<String> findPermissionIds(String accountName);
}
