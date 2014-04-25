package com.darknight.account.permission.service;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.role.entity.Role;

import java.util.List;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
public interface PermissionService {
    /**
     *
     * @param roleList
     * @return
     */
    List<Permission> findByRoles(List<Role> roleList);
}
