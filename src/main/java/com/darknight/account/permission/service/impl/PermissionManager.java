package com.darknight.account.permission.service.impl;

import com.darknight.account.permission.dao.PermissionDao;
import com.darknight.account.permission.entity.Permission;
import com.darknight.account.permission.service.PermissionService;
import com.darknight.account.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
@Component
@Transactional(readOnly = true)
public class PermissionManager implements PermissionService {
    private PermissionDao permissionDao;

    @Autowired
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public List<Permission> findByRoles(List<Role> roleList) {
        List<Permission> permissionList = permissionDao.findByRoleList(roleList);
        return permissionList;
    }
}
