package com.darknight.platform.account.role.service.impl;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.role.dao.RoleDao;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.account.role.service.RoleService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
