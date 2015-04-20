package com.darknight.platform.account.permission.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.core.base.entity.TreeEntity;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.platform.account.permission.dao.PermissionDao;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.permission.entity.PermissionNode;
import com.darknight.platform.account.permission.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by DarKnight on 2014/4/26 0026.
 */
@Service
@Transactional(readOnly = true)
public class PermissionManager extends BaseManager<Permission, String> implements PermissionService {
    private final Logger logger = LoggerFactory.getLogger(PermissionManager.class);
    private PermissionDao permissionDao;

    @Resource
    public void setBaseDao(BaseJpaDao<Permission, String> baseDao) {
        super.setBaseDao(baseDao);
        this.permissionDao = (PermissionDao)baseDao;
    }

    /**
     * 根据角色ID, 查询该角色对应的权限对象列表
     * @param roleId 角色ID
     * @return List<Permission> 权限对象列表
     */
    @Override
    public List<Permission> findPermissionListByRoleId(String roleId) {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的权限对象
        Criteria criteria = getOrderedVisibleCriteria();
        criteria.createAlias("roleList", "role").add(Restrictions.eq("role.id", roleId));
        List<Permission> permissionList = criteria.list();
        // 处理潜在空指针异常
        if(permissionList == null) {
            permissionList = new ArrayList<>();
        }
        return permissionList;
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

    /**
     * 查询所有未逻辑删除的顶级权限列表
     * @return
     */
    @Override
    public List<Permission> findAllVisibleTopPermission() {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的系统菜单对象
        Criteria criteria = getOrderedVisibleCriteria();
        criteria.add(Restrictions.isNull("parent"));
        List<Permission> permissionList = criteria.list();
        return permissionList;
    }

    /**
     * 根据当前权限实体查询所有未逻辑删除的子级权限列表
     * @param permission 权限实体
     * @return
     */
    @Override
    public List<Permission> findVisibleChildren(Permission permission) {
        List<Permission> children = permission.getChildren();
        if(children != null && !children.isEmpty()) {
            List<Permission> visibleChildren = new ArrayList<>();
            for(Permission child : children) {
                if(StringUtils.equals(DefaultEntity.VisibleTag.YES, child.getVisibleTag())) {
                    visibleChildren.add(child);
                }
            }
            return visibleChildren;
        }
        return null;
    }

    /**
     * 通过权限对象生成对应树型对象
     * @param permission 权限对象
     * @return
     */
    @Override
    public PermissionNode makeNode(Permission permission) {
        if(permission != null) {
            PermissionNode permissionNode = new PermissionNode();
            permissionNode.setId(permission.getId());
            permissionNode.setText(permission.getName());
            permissionNode.setSort(permission.getSort());
            // 判断是否存在父级权限
            if(permission.getParent() != null) {
                permissionNode.setParentId(permission.getParent().getId());
            }
            // 判断是否存在未逻辑删除的子级权限
            List<Permission> children = findVisibleChildren(permission);
            if(children != null && !children.isEmpty()) {
                // 存在子级菜单时才设定该菜单状态，并设定默认为展开
                permissionNode.setState(TreeEntity.State.OPEN);
                // 生成子菜单列表
                List<PermissionNode> childrenNode = new ArrayList<>();
                for(Permission child : children) {
                    PermissionNode childNode = makeNode(child);
                    childrenNode.add(childNode);
                }
                permissionNode.setChildren(childrenNode);
            }else {

            }

            return permissionNode;
        }else {
            logger.info("PermissionManager.makeNode(Permission permission)异常");
            logger.info("其中permission为null");
        }
        return null;
    }

    /**
     * 通过权限对象列表生成对应树型对象列表
     * @param permissionList 权限对象列表
     * @return
     */
    @Override
    public List<PermissionNode> makeNode(List<Permission> permissionList) {
        List<PermissionNode> permissionNodeList = new ArrayList<>();
        if(permissionList != null && !permissionList.isEmpty()) {
            PermissionNode permissionNode = null;
            for(Permission permission : permissionList) {
                permissionNode = makeNode(permission);
                permissionNodeList.add(permissionNode);
            }
        }else {
            logger.info("PermissionManager.makeNode(List<Permission> permissionList)异常");
            logger.info("其中permissionList为null或empty");
        }
        return permissionNodeList;
    }
}
