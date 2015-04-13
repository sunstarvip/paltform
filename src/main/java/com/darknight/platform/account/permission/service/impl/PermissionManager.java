package com.darknight.platform.account.permission.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.core.base.service.impl.BaseManager;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
@Service
@Transactional(readOnly = true)
public class PermissionManager extends BaseManager<Permission, String> implements PermissionService {
    private PermissionDao permissionDao;

    @Resource
    public void setBaseDao(BaseJpaDao<Permission, String> baseDao) {
        super.setBaseDao(baseDao);
        this.permissionDao = (PermissionDao)baseDao;
    }

    /**
     * 根据用户登录账户名称, 查询该用户对应的权限对象
     * @param accountName
     * @return
     */
    @Override
    public Set<Permission> findPermissions(String accountName) {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getVisibleCriteria();
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
        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getOrderedVisibleCriteria();
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
