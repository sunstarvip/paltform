package com.darknight.platform.account.role.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.platform.account.role.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface RoleService extends BaseService<Role, String> {

    /**
     * 根据用户ID, 查询该用户对应的角色对象列表
     * @param userId 用户ID
     * @return
     */
    List<Role> findRoleListByUserId(String userId);

    /**
     * 根据用户登录账户名称, 查询该用户对应的角色对象
     * @param accountName
     * @return
     */
    Set<Role> findRoleSetByAccountName(String accountName);

    /**
     * 根据传入的角色集合, 生成该集合中各角色的ID集合
     * @param roleSet
     * @return
     */
    Set<String> findRoleIds(Set<Role> roleSet);

    /**
     * 根据用户登录账户名称, 查询该用户对应的角色对象的ID集合
     * @param accountName
     * @return
     */
    Set<String> findRoleIds(String accountName);

    /**
     * 通过条件分页查询未逻辑删除的角色列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    Page<Role> findSearchPage(Map<String, Object> searchMap, Pageable page);
}
