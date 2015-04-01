package com.darknight.platform.account.role.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.core.base.service.impl.BaseManager;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
@Service
@Transactional(readOnly = true)
public class RoleManager extends BaseManager<Role, String> implements RoleService{
    private RoleDao roleDao;

    @Resource
    public void setBaseDao(BaseJpaDao<Role, String> baseDao) {
        super.setBaseDao(baseDao);
        this.roleDao = (RoleDao)baseDao;
    }

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
