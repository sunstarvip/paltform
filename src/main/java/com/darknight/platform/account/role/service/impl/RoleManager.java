package com.darknight.platform.account.role.service.impl;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.role.dao.RoleDao;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
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
 * Created by DarKnight on 2014/5/22 0022.
 */
@Component
@Transactional(readOnly = true)
public class RoleManager implements RoleService{
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role find(String roleId) {
        Role role = roleDao.getOne(roleId);
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = roleDao.findAll();
        if(roleList == null) {
            roleList = new ArrayList<>();
        }
        return roleList;
    }

    @Override
    public Page<Role> findAll(Pageable page) {
        Page<Role> rolePage = roleDao.findAll(page);
        return rolePage;
    }

    @Override
    @Transactional(readOnly = false)
    public void flush() {
        roleDao.flush();
    }

    @Override
    @Transactional(readOnly = false)
    public Role save(Role role) {
        return roleDao.saveAndFlush(role);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Role> save(List<Role> roleList) {
        roleList = roleDao.save(roleList);
        flush();
        return roleList;
    }

    @Override
    public Set<Role> findRoles(String accountName) {
        Criteria criteria = roleDao.createCriteria();
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        criteria.add(Restrictions.eq("enableTag", DefaultEntity.EnableTag.YES));
        criteria.createAlias("userList", "user").add(Restrictions.eq("user.accountName", accountName));
        Set<Role> roleSet = new HashSet<Role>(criteria.list());
        return roleSet;
    }

    @Override
    public Set<String> findRoleIds(Set<Role> roleSet) {
        Set<String> roleIdSet = new HashSet<>();
        for(Role role : roleSet) {
            roleIdSet.add(role.getId());
        }
        return roleIdSet;
    }

    @Override
    public Set<String> findRoleIds(String accountName) {
        Set<Role> roleSet = findRoles(accountName);
        Set<String> roleIdSet = findRoleIds(roleSet);
        return roleIdSet;
    }
}
