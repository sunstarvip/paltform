package com.darknight.account.role.service.impl;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.role.dao.RoleDao;
import com.darknight.account.role.entity.Role;
import com.darknight.account.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by DarKnight on 2014/4/25 0025.
 */
@Component
@Transactional(readOnly = true)
public class RoleManager implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
