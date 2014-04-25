package com.darknight.account.permission.dao;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.role.entity.Role;
import com.darknight.base.dao.DefaultJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
public interface PermissionDao extends DefaultJpaRepository<Permission, String> {

    /**
     * 通过角色集合查询该集合中所有角色总共含有的权限集合
     * @param roleList
     * @return
     */
    @Query("select p from Permission p where p.roleList in ?1 order by p.sort desc")
    List<Permission> findByRoleList(List<Role> roleList);
}
