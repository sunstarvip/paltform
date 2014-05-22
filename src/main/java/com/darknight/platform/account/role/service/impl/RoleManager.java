package com.darknight.platform.account.role.service.impl;

import com.darknight.platform.account.role.dao.RoleDao;
import com.darknight.platform.account.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
@Component
@Transactional(readOnly = true)
public class RoleManager implements RoleService{
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
