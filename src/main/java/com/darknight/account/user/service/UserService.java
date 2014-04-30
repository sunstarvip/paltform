package com.darknight.account.user.service;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.role.entity.Role;
import com.darknight.account.user.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by DarKnight on 14-2-5.
 */
public interface UserService {
    User find(String userId);

    User save(User user);

    void flush();

    void delete(User user);

    User findByAccountName(String accountName);

    String getCredentialsSalt(String accountName);

    void changePassword(String userId, String newPassword);

    List<Role> findRoleList(String accountName);

    Set<Role> findRoleSet(String accountName);

    List<String> findRoleIdList(String accountName);

    Set<String> findRoleIdSet(String accountName);

    List<Permission> findPermissionList(String accountName);

    Set<Permission> findPermissionSet(String accountName);

    List<String> findPermissionIdList(String accountName);

    Set<String> findPermissionIdSet(String accountName);
}
