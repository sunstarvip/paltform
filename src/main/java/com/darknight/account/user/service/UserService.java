package com.darknight.account.user.service;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.role.entity.Role;

import java.util.List;

/**
 * Created by DarKnight on 14-2-5.
 */
public interface UserService {
    List<Role> findRoles(String accountName);

    List<Permission> findPermissions(String accountName);
}
