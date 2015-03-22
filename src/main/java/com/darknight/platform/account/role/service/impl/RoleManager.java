package com.darknight.platform.account.role.service.impl;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.role.dao.RoleDao;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
@Service
@Transactional(readOnly = true)
public class RoleManager implements RoleService{
    private RoleDao roleDao;

    @Resource
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    //通用方法区域 Begin
    /**
     * 推送缓存中的数据操作至数据库
     */
    @Override
    @Transactional(readOnly = false)
    public void flush() {
        roleDao.flush();
    }

    /**
     * 保存Role角色对象
     * @param Role
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Role save(Role Role) {
        return roleDao.saveAndFlush(Role);
    }

    /**
     * 批量保存Role角色对象
     * @param RoleList
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public List<Role> save(List<Role> RoleList) {
        RoleList = roleDao.save(RoleList);
        flush();
        return RoleList;
    }

    /**
     * 删除该角色ID下的Role角色对象
     * @param roleId
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(String roleId) {
        roleDao.delete(roleId);
        flush();
    }

    /**
     * 根据传入角色ID, 批量删除Role角色对象
     * @param idList
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(List<String> idList) {
        for(String roleId : idList) {
            roleDao.delete(roleId);
        }
        flush();
    }

    /**
     * 删除Role角色对象
     * @param role
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(Role role) {
        roleDao.delete(role);
        flush();
    }

    /**
     * 批量删除Role角色对象
     * @param roleList
     */
    @Override
    @Transactional(readOnly = false)
    public void realDeleteInBatch(List<Role> roleList) {
        roleDao.deleteInBatch(roleList);
        flush();
    }

    /**
     * 删除所有的Role角色对象
     */
    @Override
    @Transactional(readOnly = false)
    public void realDeleteAll() {
        roleDao.deleteAll();
        flush();
    }

    /**
     * 统计所有Role角色对象的总数量
     * @return
     */
    @Override
    public long count() {
        return roleDao.count();
    }

    /**
     * 根据传入角色ID, 判断该角色是否存在
     * @param roleId
     * @return
     */
    @Override
    public boolean exists(String roleId) {
        return roleDao.exists(roleId);
    }

    /**
     * 根据角色ID, 查询Role角色对象
     * @param roleId
     * @return
     */
    @Override
    public Role find(String roleId) {
        return roleDao.getOne(roleId);
    }

    /**
     * 根据传入的角色ID, 批量查询Role角色对象
     * @param idList
     * @return
     */
    @Override
    public List<Role> find(List<String> idList) {
        List<Role> roleList = roleDao.findAll(idList);
        if(roleList == null) {
            roleList = new ArrayList<Role>();
        }
        return roleList;
    }

    /**
     * 查询所有的Role角色对象
     * @return
     */
    @Override
    public List<Role> findAll() {
        List<Role> roleList = roleDao.findAll();
        if(roleList == null) {
            roleList = new ArrayList<Role>();
        }
        return roleList;
    }

    /**
     * 分页查询所有的Role角色对象
     * @param page
     * @return
     */
    @Override
    public Page<Role> findAll(Pageable page) {
        Page<Role> rolePage = roleDao.findAll(page);
        return rolePage;
    }

    /**
     * 查询所有的Role角色对象, 并根据Sort排序规则进行排序
     * @param sort
     * @return
     */
    @Override
    public List<Role> findAll(Sort sort) {
        List<Role> roleList = roleDao.findAll(sort);
        if(roleList == null) {
            roleList = new ArrayList<Role>();
        }
        return roleList;
    }

    /**
     * 查询所有未逻辑删除的Role角色对象
     * @return
     */
    @Override
    public List<Role> findAllVisible() {
        // 创建查询对象
        Criteria criteria = roleDao.createCriteria();
        // 添加查询规则
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        List<Role> roleList = criteria.list();
        return roleList;
    }

    /**
     * 删除该角色ID下的Role角色对象
     * 逻辑删除
     * @param roleId 角色ID
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String roleId) {
        if(exists(roleId)) {
            Role role = find(roleId);
            role.setUpdateTime(new Date());
            role.setVisibleTag(DefaultEntity.VisibleTag.NO);
            save(role);
        }
    }

    /**
     * 根据传入角色ID, 批量删除Role角色对象
     * 逻辑删除
     * @param idList 角色ID列表
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(List<String> idList) {
        List<Role> roleList = find(idList);
        if(!roleList.isEmpty()) {
            for(Role role : roleList) {
                role.setUpdateTime(new Date());
                role.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(roleList);
        }
    }

    /**
     * 删除Role角色对象
     * 逻辑删除
     * @param role 角色对象
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Role role) {
        role.setUpdateTime(new Date());
        role.setVisibleTag(DefaultEntity.VisibleTag.NO);
        save(role);
    }

    /**
     * 批量删除Role角色对象
     * 逻辑删除
     * @param roleList 角色对象列表
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteInBatch(List<Role> roleList) {
        if(!roleList.isEmpty()) {
            for(Role role : roleList) {
                role.setUpdateTime(new Date());
                role.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(roleList);
        }
    }

    /**
     * 删除所有的Role角色对象
     * 逻辑删除
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteAll() {
        List<Role> roleList = findAllVisible();
        if(!roleList.isEmpty()) {
            for(Role role : roleList) {
                role.setUpdateTime(new Date());
                role.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(roleList);
        }
    }

    //通用方法区域 End

    /**
     * 根据用户登录账户名称, 查询该用户对应的角色对象
     * @param accountName
     * @return
     */
    @Override
    public Set<Role> findRoles(String accountName) {
        Criteria criteria = roleDao.createCriteria();
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        criteria.add(Restrictions.eq("enableTag", DefaultEntity.EnableTag.YES));
        criteria.createAlias("RoleList", "Role").add(Restrictions.eq("Role.accountName", accountName));
        Set<Role> roleSet = new HashSet<Role>(criteria.list());
        return roleSet;
    }

    /**
     * 根据传入的角色集合, 生成该集合中各角色的ID集合
     * @param roleSet
     * @return
     */
    @Override
    public Set<String> findRoleIds(Set<Role> roleSet) {
        Set<String> roleIdSet = new HashSet<>();
        for(Role role : roleSet) {
            roleIdSet.add(role.getId());
        }
        return roleIdSet;
    }

    /**
     * 根据用户登录账户名称, 查询该用户对应的角色对象的ID集合
     * @param accountName
     * @return
     */
    @Override
    public Set<String> findRoleIds(String accountName) {
        Set<Role> roleSet = findRoles(accountName);
        Set<String> roleIdSet = findRoleIds(roleSet);
        return roleIdSet;
    }

    @Override
    public Page<Role> findSearchPage(Map<String, Object> searchMap, Pageable page) {
        // 创建查询对象
        Criteria criteria = roleDao.createCriteria();
        // 添加查询规则
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        for(Map.Entry<String, Object> searchEntry: searchMap.entrySet()) {
            if(searchEntry.getValue() != null && StringUtils.isNotBlank(searchEntry.getValue().toString())) {
                if(StringUtils.contains(searchEntry.getKey(), "like_")) {
                    criteria.add(Restrictions.like(
                            StringUtils.replace(searchEntry.getKey(), "like_", ""),
                            "%" + searchEntry.getValue() + "%"
                    ));
                }else {
                    criteria.add(Restrictions.eq(searchEntry.getKey(), searchEntry.getValue()));
                }
            }
        }
        //统计数据总数
        criteria.setProjection(Projections.rowCount());
        Object result = (criteria.uniqueResult() != null)?criteria.uniqueResult():0;
        Long totalNum = Long.valueOf(result.toString());
        criteria.setProjection(null);

        //设定查询起始值
        criteria.setFirstResult(page.getOffset());
        //设定查询分页大小
        criteria.setMaxResults(page.getPageSize());
        Page<Role> rolePage = new PageImpl(criteria.list(), page, totalNum);
        return rolePage;
    }
}
