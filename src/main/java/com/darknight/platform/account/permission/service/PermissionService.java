package com.darknight.platform.account.permission.service;

import com.darknight.platform.account.permission.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
public interface PermissionService {

    //通用方法区域 Begin
    public void flush();

    public Permission save(Permission permission);

    public List<Permission> save(List<Permission> permissionList);

    public void realDelete(String permissionId);

    public void realDelete(List<String> idList);

    public void realDelete(Permission permission);

    public void realDeleteInBatch(List<Permission> permissionList);

    public void realDeleteAll();

    public long count();

    public boolean exists(String permissionId);

    public Permission find(String permissionId);

    public List<Permission> find(List<String> idList);

    public List<Permission> findAll();

    public Page<Permission> findAll(Pageable page);

    public List<Permission> findAll(Sort sort);

    /**
     * 查询所有未逻辑删除的Permission权限对象
     * @return
     */
    public List<Permission> findAllVisible();

    /**
     * 删除该权限ID下的Permission权限对象
     * 逻辑删除
     * @param permissionId 权限ID
     */
    public void delete(String permissionId);

    /**
     * 根据传入权限ID, 批量删除Permission权限对象
     * 逻辑删除
     * @param idList 权限ID列表
     */
    public void delete(List<String> idList);

    /**
     * 删除Permission权限对象
     * 逻辑删除
     * @param permission 权限对象
     */
    public void delete(Permission permission);

    /**
     * 批量删除Permission权限对象
     * 逻辑删除
     * @param permissionList 权限对象列表
     */
    public void deleteInBatch(List<Permission> permissionList);

    /**
     * 删除所有的Permission权限对象
     * 逻辑删除
     */
    public void deleteAll();
    //通用方法区域 End

    public Set<Permission> findPermissions(String accountName);

    public Set<String> findPermissionIds(Set<Permission> permissionSet);

    public Set<String> findPermissionIds(String accountName);

    /**
     * 通过条件分页查询未逻辑删除的权限列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    public Page<Permission> findSearchPage(Map<String, Object> searchMap, Pageable page);
}
