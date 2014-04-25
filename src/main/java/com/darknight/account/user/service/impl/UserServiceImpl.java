package com.darknight.account.user.service.impl;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.permission.service.PermissionService;
import com.darknight.account.role.entity.Role;
import com.darknight.account.role.service.RoleService;
import com.darknight.account.user.dao.UserDao;
import com.darknight.account.user.entity.User;
import com.darknight.account.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by DarKnight on 14-2-5.
 */
@Component
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    private RoleService roleService;
    private PermissionService permissionService;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public List<Role> findRoles(String accountName) {
        User user = userDao.findByAccountName(accountName);
        List<Role> roleList = user.getRoleList();
        return roleList;
    }

    @Override
    public List<Permission> findPermissions(String accountName) {
        List<Role> roleList = findRoles(accountName);
        List<Permission> permissionList = permissionService.findByRoles(roleList);
        return permissionList;
    }
}
