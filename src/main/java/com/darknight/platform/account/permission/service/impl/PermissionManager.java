package com.darknight.platform.account.permission.service.impl;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.permission.dao.PermissionDao;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.service.PermissionService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void delete(String permissionId) {
        permissionDao.delete(permissionId);
        flush();
    }

    /**
     * 根据传入权限ID, 批量删除Permission权限对象
     * @param idList
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(List<String> idList) {
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
    public void delete(Permission permission) {
        permissionDao.delete(permission);
        flush();
    }

    /**
     * 批量删除Permission权限对象
     * @param permissionList
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteInBatch(List<Permission> permissionList) {
        permissionDao.deleteInBatch(permissionList);
        flush();
    }

    /**
     * 删除所有的Permission权限对象
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteAll() {
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
}
