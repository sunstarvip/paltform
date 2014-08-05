package com.darknight.platform.account.permission.service.impl;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.permission.dao.PermissionDao;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.service.PermissionService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
@Component
@Transactional(readOnly = true)
public class PermissionManager implements PermissionService {
    private PermissionDao permissionDao;

    @Autowired
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public Permission find(String permissionId) {
        Permission permission = permissionDao.getOne(permissionId);
        return permission;
    }

    @Override
    public List<Permission> findAll() {
        List<Permission> permissionList = permissionDao.findAll();
        if(permissionList == null) {
            permissionList = new ArrayList<>();
        }
        return permissionList;
    }

    @Override
    public Page<Permission> findAll(Pageable page) {
        Page<Permission> permissionPage = permissionDao.findAll(page);
        return permissionPage;
    }

    @Override
    @Transactional(readOnly = false)
    public void flush() {
        permissionDao.flush();
    }

    @Override
    @Transactional(readOnly = false)
    public Permission save(Permission permission) {
        return permissionDao.saveAndFlush(permission);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Permission> save(List<Permission> permissionList) {
        permissionList = permissionDao.save(permissionList);
        flush();
        return permissionList;
    }

    @Override
    public Set<Permission> findPermissions(String accountName) {
        Criteria criteria = permissionDao.createCriteria();
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        criteria.add(Restrictions.eq("enableTag", DefaultEntity.EnableTag.YES));
        criteria.createAlias("roleList", "role");
        criteria.createAlias("userList", "user").add(Restrictions.eq("role.user.accountName", accountName));
        Set<Permission> permissionSet = new HashSet<Permission>(criteria.list());
        return permissionSet;
    }

    @Override
    public Set<String> findPermissionIds(Set<Permission> permissionSet) {
        Set<String> permissionIdSet = new HashSet<>();
        for (Permission permission : permissionSet) {
            permissionIdSet.add(permission.getId());
        }
        return permissionIdSet;
    }

    @Override
    public Set<String> findPermissionIds(String accountName) {
        Set<Permission> permissionSet = findPermissions(accountName);
        Set<String> permissionIdSet = findPermissionIds(permissionSet);
        return permissionIdSet;
    }
}
