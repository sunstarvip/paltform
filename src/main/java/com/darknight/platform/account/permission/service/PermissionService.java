package com.darknight.platform.account.permission.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.platform.account.permission.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
public interface PermissionService extends BaseService<Permission, String> {



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
