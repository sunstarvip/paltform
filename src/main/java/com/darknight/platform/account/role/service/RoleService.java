package com.darknight.platform.account.role.service;

import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface RoleService {
    public Role find(String roleId);

    public List<Role> findAll();

    public Page<Role> findAll(Pageable page);

    public void flush();

    public Role save(Role role);

    public List<Role> save(List<Role> roleList);

    public Set<Role> findRoles(String accountName);

    public Set<String> findRoleIds(Set<Role> roleSet);

    public Set<String> findRoleIds(String accountName);
}
