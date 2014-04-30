package com.darknight.account.user.service.impl;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.permission.service.PermissionService;
import com.darknight.account.role.entity.Role;
import com.darknight.account.role.service.RoleService;
import com.darknight.account.user.dao.UserDao;
import com.darknight.account.user.entity.User;
import com.darknight.account.user.service.UserService;
import com.darknight.security.shiro.password.PasswordMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DarKnight on 14-2-5.
 */
@Component
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    private RoleService roleService;
    private PermissionService permissionService;
    private PasswordMaker passwordMaker;

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

    @Autowired
    public void setPasswordMaker(PasswordMaker passwordMaker) {
        this.passwordMaker = passwordMaker;
    }

    @Override
    public User find(String userId) {
        return userDao.findOne(userId);
    }

    @Override
    @Transactional(readOnly = false)
    public User save(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    public void flush() {
        userDao.flush();
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(User user) {
        userDao.delete(user);
        flush();
    }

    @Override
    @Transactional(readOnly = false)
    public User createUser(User user) {
        user = passwordMaker.encryptPassword(user);
        save(user);
        return user;
    }

    @Override
    public User findByAccountName(String accountName) {
        User user = userDao.findByAccountName(accountName);
        return user;
    }

    @Override
    public String getCredentialsSalt(String accountName) {
        User user = findByAccountName(accountName);
        return user.getAccountName() + user.getSalt();
    }

    @Override
    @Transactional(readOnly = false)
    public User changePassword(String userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(newPassword);
        user = passwordMaker.encryptPassword(user);
        save(user);
        return user;
    }

    @Override
    public List<Role> findRoleList(String accountName) {
        User user = userDao.findByAccountName(accountName);
        List<Role> roleList = user.getRoleList();
        return roleList;
    }

    @Override
    public Set<Role> findRoleSet(String accountName) {
        List<Role> roleList = findRoleList(accountName);
        Set<Role> roleSet = new HashSet<>(roleList);
        return roleSet;
    }

    @Override
    public List<String> findRoleIdList(String accountName) {
        List<Role> roleList = findRoleList(accountName);
        List<String> roleIdList = new ArrayList<>();
        for(Role role : roleList) {
            roleIdList.add(role.getId());
        }
        return roleIdList;
    }

    @Override
    public Set<String> findRoleIdSet(String accountName) {
        List<Role> roleList = findRoleList(accountName);
        Set<String> roleIdSet = new HashSet<>();
        for(Role role : roleList) {
            roleIdSet.add(role.getId());
        }
        return roleIdSet;
    }

    @Override
    public List<Permission> findPermissionList(String accountName) {
        List<Role> roleList = findRoleList(accountName);
        List<Permission> permissionList = permissionService.findByRoles(roleList);
        return permissionList;
    }

    @Override
    public Set<Permission> findPermissionSet(String accountName) {
        List<Permission> permissionList = findPermissionList(accountName);
        Set<Permission> permissionSet = new HashSet<>(permissionList);
        return permissionSet;
    }

    @Override
    public List<String> findPermissionIdList(String accountName) {
        List<Permission> permissionList = findPermissionList(accountName);
        List<String> permissionIdList = new ArrayList<>();
        for(Permission permission : permissionList) {
            permissionIdList.add(permission.getId());
        }
        return permissionIdList;
    }

    @Override
    public Set<String> findPermissionIdSet(String accountName) {
        List<Permission> permissionList = findPermissionList(accountName);
        Set<String> permissionIdSet = new HashSet<>();
        for(Permission permission : permissionList) {
            permissionIdSet.add(permission.getId());
        }
        return permissionIdSet;
    }
}
