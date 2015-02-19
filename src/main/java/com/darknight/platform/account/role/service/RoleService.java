package com.darknight.platform.account.role.service;

import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface RoleService {

    //通用方法区域 Begin
    public void flush();

    public Role save(Role role);

    public List<Role> save(List<Role> roleList);

    public void delete(String roleId);

    public void delete(List<String> idList);

    public void delete(Role role);

    public void deleteInBatch(List<Role> roleList);

    public void deleteAll();

    public long count();

    public boolean exists(String roleId);

    public Role find(String roleId);

    public List<Role> find(List<String> idList);

    public List<Role> findAll();

    public Page<Role> findAll(Pageable page);

    public List<Role> findAll(Sort sort);
    //通用方法区域 End

    public Set<Role> findRoles(String accountName);

    public Set<String> findRoleIds(Set<Role> roleSet);

    public Set<String> findRoleIds(String accountName);
}
