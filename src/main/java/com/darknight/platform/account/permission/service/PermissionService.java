package com.darknight.platform.account.permission.service;

import com.darknight.platform.account.permission.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
public interface PermissionService {

    //通用方法区域 Begin
    public void flush();

    public Permission save(Permission permission);

    public List<Permission> save(List<Permission> permissionList);

    public void delete(String permissionId);

    public void delete(List<String> idList);

    public void delete(Permission permission);

    public void deleteInBatch(List<Permission> permissionList);

    public void deleteAll();

    public long count();

    public boolean exists(String permissionId);

    public Permission find(String permissionId);

    public List<Permission> find(List<String> idList);

    public List<Permission> findAll();

    public Page<Permission> findAll(Pageable page);

    public List<Permission> findAll(Sort sort);
    //通用方法区域 End

    public Set<Permission> findPermissions(String accountName);

    public Set<String> findPermissionIds(Set<Permission> permissionSet);

    public Set<String> findPermissionIds(String accountName);
}
