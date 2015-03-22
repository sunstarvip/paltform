package com.darknight.platform.account.permission.service.impl;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.permission.dao.PermissionDao;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.service.PermissionService;
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
 * Created by DarKnight on 2014/4/26 0026.
 */
@Service
@Transactional(readOnly = true)
public class PermissionManager implements PermissionService {
    private PermissionDao permissionDao;

    @Resource
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    //通用方法区域 Begin
    /**
     * 推送缓存中的数据操作至数据库
     */
    @Override
    @Transactional(readOnly = false)
    public void flush() {
        permissionDao.flush();
    }

    /**
     * 保存Permission权限对象
     * @param permission
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Permission save(Permission permission) {
        return permissionDao.saveAndFlush(permission);
    }

    /**
     * 批量保存Permission权限对象
     * @param permissionList
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public List<Permission> save(List<Permission> permissionList) {
        permissionList = permissionDao.save(permissionList);
        flush();
        return permissionList;
    }

    /**
     * 删除该权限ID下的Permission权限对象
     * @param permissionId
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(String permissionId) {
        permissionDao.delete(permissionId);
        flush();
    }

    /**
     * 根据传入权限ID, 批量删除Permission权限对象
     * @param idList
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(List<String> idList) {
        for(String permissionId : idList) {
            permissionDao.delete(permissionId);
        }
        flush();
    }

    /**
     * 删除Permission权限对象
     * @param permission
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(Permission permission) {
        permissionDao.delete(permission);
        flush();
    }

    /**
     * 批量删除Permission权限对象
     * @param permissionList
     */
    @Override
    @Transactional(readOnly = false)
    public void realDeleteInBatch(List<Permission> permissionList) {
        permissionDao.deleteInBatch(permissionList);
        flush();
    }

    /**
     * 删除所有的Permission权限对象
     */
    @Override
    @Transactional(readOnly = false)
    public void realDeleteAll() {
        permissionDao.deleteAll();
        flush();
    }

    /**
     * 统计所有Permission权限对象的总数量
     * @return
     */
    @Override
    public long count() {
        return permissionDao.count();
    }

    /**
     * 根据传入权限ID, 判断该权限是否存在
     * @param permissionId
     * @return
     */
    @Override
    public boolean exists(String permissionId) {
        return permissionDao.exists(permissionId);
    }

    /**
     * 根据权限ID, 查询Permission权限对象
     * @param permissionId
     * @return
     */
    @Override
    public Permission find(String permissionId) {
        return permissionDao.getOne(permissionId);
    }

    /**
     * 根据传入的权限ID, 批量查询Permission权限对象
     * @param idList
     * @return
     */
    @Override
    public List<Permission> find(List<String> idList) {
        List<Permission> permissionList = permissionDao.findAll(idList);
        if(permissionList == null) {
            permissionList = new ArrayList<Permission>();
        }
        return permissionList;
    }

    /**
     * 查询所有的Permission权限对象
     * @return
     */
    @Override
    public List<Permission> findAll() {
        List<Permission> permissionList = permissionDao.findAll();
        if(permissionList == null) {
            permissionList = new ArrayList<Permission>();
        }
        return permissionList;
    }

    /**
     * 分页查询所有的Permission权限对象
     * @param page
     * @return
     */
    @Override
    public Page<Permission> findAll(Pageable page) {
        Page<Permission> permissionPage = permissionDao.findAll(page);
        return permissionPage;
    }

    /**
     * 查询所有的Permission权限对象, 并根据Sort排序规则进行排序
     * @param sort
     * @return
     */
    @Override
    public List<Permission> findAll(Sort sort) {
        List<Permission> permissionList = permissionDao.findAll(sort);
        if(permissionList == null) {
            permissionList = new ArrayList<Permission>();
        }
        return permissionList;
    }

    /**
     * 查询所有未逻辑删除的Permission权限对象
     * @return
     */
    @Override
    public List<Permission> findAllVisible() {
        // 创建查询对象
        Criteria criteria = permissionDao.createCriteria();
        // 添加查询规则
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        List<Permission> permissionList = criteria.list();
        return permissionList;
    }

    /**
     * 删除该权限ID下的Permission权限对象
     * 逻辑删除
     * @param permissionId 权限ID
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String permissionId) {
        if(exists(permissionId)) {
            Permission permission = find(permissionId);
            permission.setUpdateTime(new Date());
            permission.setVisibleTag(DefaultEntity.VisibleTag.NO);
            save(permission);
        }
    }

    /**
     * 根据传入权限ID, 批量删除Permission权限对象
     * 逻辑删除
     * @param idList 权限ID列表
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(List<String> idList) {
        List<Permission> permissionList = find(idList);
        if(!permissionList.isEmpty()) {
            for(Permission permission : permissionList) {
                permission.setUpdateTime(new Date());
                permission.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(permissionList);
        }
    }

    /**
     * 删除Permission权限对象
     * 逻辑删除
     * @param permission 权限对象
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Permission permission) {
        permission.setUpdateTime(new Date());
        permission.setVisibleTag(DefaultEntity.VisibleTag.NO);
        save(permission);
    }

    /**
     * 批量删除Permission权限对象
     * 逻辑删除
     * @param permissionList 权限对象列表
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteInBatch(List<Permission> permissionList) {
        if(!permissionList.isEmpty()) {
            for(Permission permission : permissionList) {
                permission.setUpdateTime(new Date());
                permission.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(permissionList);
        }
    }

    /**
     * 删除所有的Permission权限对象
     * 逻辑删除
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteAll() {
        List<Permission> permissionList = findAllVisible();
        if(!permissionList.isEmpty()) {
            for(Permission permission : permissionList) {
                permission.setUpdateTime(new Date());
                permission.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(permissionList);
        }
    }
    //通用方法区域 End

    /**
     * 根据用户登录账户名称, 查询该用户对应的权限对象
     * @param accountName
     * @return
     */
    @Override
    public Set<Permission> findPermissions(String accountName) {
        Criteria criteria = permissionDao.createCriteria();
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        criteria.add(Restrictions.eq("enableTag", DefaultEntity.EnableTag.YES));
        criteria.createAlias("PermissionList", "Permission");
        criteria.createAlias("userList", "user").add(Restrictions.eq("Permission.user.accountName", accountName));
        Set<Permission> permissionSet = new HashSet<Permission>(criteria.list());
        return permissionSet;
    }

    /**
     * 根据传入的权限集合, 生成该集合中各权限的ID集合
     * @param permissionSet
     * @return
     */
    @Override
    public Set<String> findPermissionIds(Set<Permission> permissionSet) {
        Set<String> permissionIdSet = new HashSet<>();
        for (Permission permission : permissionSet) {
            permissionIdSet.add(permission.getId());
        }
        return permissionIdSet;
    }

    /**
     * 根据用户登录账户名称, 查询该用户对应的权限对象的ID集合
     * @param accountName
     * @return
     */
    @Override
    public Set<String> findPermissionIds(String accountName) {
        Set<Permission> permissionSet = findPermissions(accountName);
        Set<String> permissionIdSet = findPermissionIds(permissionSet);
        return permissionIdSet;
    }

    /**
     * 通过条件分页查询未逻辑删除的权限列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<Permission> findSearchPage(Map<String, Object> searchMap, Pageable page) {
        // 创建查询对象
        Criteria criteria = permissionDao.createCriteria();
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
        Page<Permission> permissionPage = new PageImpl(criteria.list(), page, totalNum);
        return permissionPage;
    }
}
