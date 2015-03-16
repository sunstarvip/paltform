package com.darknight.platform.account.role.service;

import com.darknight.platform.account.role.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface RoleService {

    //通用方法区域 Begin
    /**
     * 推送缓存中的数据操作至数据库
     */
    public void flush();

    /**
     * 保存角色对象
     * @param role 角色对象
     * @return
     */
    public Role save(Role role);

    public List<Role> save(List<Role> roleList);

    /**
     * 删除该角色ID下的Role角色对象
     * 物理删除
     * @param roleId 角色ID
     */
    public void realDelete(String roleId);

    /**
     * 根据传入角色ID, 批量删除Role角色对象
     * 物理删除
     * @param idList 角色ID列表
     */
    public void realDelete(List<String> idList);

    /**
     * 删除Role角色对象
     * 物理删除
     * @param role Role角色对象
     */
    public void realDelete(Role role);

    /**
     * 批量删除Role角色对象
     * 物理删除
     * @param roleList 角色对象列表
     */
    public void realDeleteInBatch(List<Role> roleList);

    /**
     * 删除所有的Role角色对象
     * 物理删除
     */
    public void realDeleteAll();

    public long count();

    public boolean exists(String roleId);

    public Role find(String roleId);

    public List<Role> find(List<String> idList);

    public List<Role> findAll();

    public Page<Role> findAll(Pageable page);

    public List<Role> findAll(Sort sort);

    /**
     * 查询所有未逻辑删除的Role角色对象
     * @return
     */
    public List<Role> findAllVisible();

    /**
     * 删除该角色ID下的Role角色对象
     * 逻辑删除
     * @param roleId 角色ID
     */
    public void delete(String roleId);

    /**
     * 根据传入角色ID, 批量删除Role角色对象
     * 逻辑删除
     * @param idList 角色ID列表
     */
    public void delete(List<String> idList);

    /**
     * 删除Role角色对象
     * 逻辑删除
     * @param role 角色对象
     */
    public void delete(Role role);

    /**
     * 批量删除Role角色对象
     * 逻辑删除
     * @param roleList 角色对象列表
     */
    public void deleteInBatch(List<Role> roleList);

    /**
     * 删除所有的Role角色对象
     * 逻辑删除
     */
    public void deleteAll();
    //通用方法区域 End

    /**
     * 根据用户登录账户名称, 查询该用户对应的角色对象
     * @param accountName
     * @return
     */
    public Set<Role> findRoles(String accountName);

    /**
     * 根据传入的角色集合, 生成该集合中各角色的ID集合
     * @param roleSet
     * @return
     */
    public Set<String> findRoleIds(Set<Role> roleSet);

    /**
     * 根据用户登录账户名称, 查询该用户对应的角色对象的ID集合
     * @param accountName
     * @return
     */
    public Set<String> findRoleIds(String accountName);

    /**
     * 通过条件分页查询未逻辑删除的角色列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    public Page<Role> findSearchPage(Map<String, Object> searchMap, Pageable page);
}
