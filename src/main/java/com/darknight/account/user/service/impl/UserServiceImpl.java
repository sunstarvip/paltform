package com.darknight.account.user.service.impl;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.role.entity.Role;
import com.darknight.account.user.dao.UserDao;
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

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Role> findRoles(String accountName) {
        return null;
    }

    @Override
    public List<Permission> findPermissions(String accountName) {
        return null;
    }
}
