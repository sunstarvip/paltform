package com.darknight.platform.account.permission.service.impl;

import com.darknight.platform.account.permission.dao.PermissionDao;
import com.darknight.platform.account.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
}
