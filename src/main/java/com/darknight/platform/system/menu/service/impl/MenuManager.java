package com.darknight.platform.system.menu.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.core.base.entity.TreeEntity;
import com.darknight.core.base.service.impl.BaseManager;
import com.darknight.platform.system.menu.dao.MenuDao;
import com.darknight.platform.system.menu.entity.Menu;
import com.darknight.platform.system.menu.entity.MenuNode;
import com.darknight.platform.system.menu.service.MenuService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DarKnight on 2015/3/17.
 */
@Service
@Transactional(readOnly = true)
public class MenuManager extends BaseManager<Menu, String> implements MenuService {
    private final Logger logger = LoggerFactory.getLogger(MenuManager.class);
    private MenuDao menuDao;

    @Resource
    public void setBaseDao(BaseJpaDao<Menu, String> baseDao) {
        super.setBaseDao(baseDao);
        this.menuDao = (MenuDao)baseDao;
    }

    /**
     * 通过条件分页查询系统菜单列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<Menu> findSearchPage(Map<String, Object> searchMap, Pageable page) {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的系统菜单对象
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
        Page<Menu> menuPage = new PageImpl(criteria.list(), page, totalNum);
        return menuPage;
    }

    /**
     * 查询所有未逻辑删除的顶级系统菜单列表
     * @return
     */
    @Override
    public List<Menu> findAllVisibleTopMenu() {
        // 获取自定义查询对象，查询未逻辑删除并默认排序的系统菜单对象
        Criteria criteria = getOrderedVisibleCriteria();
        criteria.add(Restrictions.isNull("parent"));
        List<Menu> menuList = criteria.list();
        return menuList;
    }

    /**
     * 根据当前系统菜单实体查询所有未逻辑删除的子级系统菜单列表
     * @param menu 系统菜单实体
     * @return
     */
    @Override
    public List<Menu> findVisibleChildren(Menu menu) {
        List<Menu> children = menu.getChildren();
        if(children != null && !children.isEmpty()) {
            List<Menu> visibleChildren = new ArrayList<>();
            for(Menu child : children) {
                if(StringUtils.equals(DefaultEntity.VisibleTag.YES, child.getVisibleTag())) {
                    visibleChildren.add(child);
                }
            }
            return visibleChildren;
        }
        return null;
    }

    /**
     * 通过系统菜单对象生成对应树型对象
     * @param menu 系统菜单对象
     * @return
     */
    @Override
    public MenuNode makeNode(Menu menu) {
        if(menu != null) {
            MenuNode menuNode = new MenuNode();
            menuNode.setId(menu.getId());
            menuNode.setText(menu.getName());
            menuNode.setSort(menu.getSort());
            // 判断是否存在父级菜单
            if(menu.getParent() != null) {
                menuNode.setParentId(menu.getParent().getId());
            }
            // 判断是否存在未逻辑删除的子级菜单
            List<Menu> children = findVisibleChildren(menu);
            if(children != null && !children.isEmpty()) {
                // 存在子级菜单时才设定该菜单状态，并设定默认为展开
                menuNode.setState(TreeEntity.State.OPEN);
                // 生成子菜单列表
                List<MenuNode> childrenNode = new ArrayList<>();
                for(Menu child : children) {
                    MenuNode childNode = makeNode(child);
                    childrenNode.add(childNode);
                }
                menuNode.setChildren(childrenNode);
            }else {

            }

            menuNode.setUrlPath(menu.getUrlPath());

            return menuNode;
        }else {
            logger.info("MenuManager.makeNode(Menu menu)异常");
            logger.info("其中menu为null");
        }
        return null;
    }

    /**
     * 通过系统菜单对象列表生成对应树型对象列表，
     * 并生成层级关系
     * @param menuList 系统菜单对象列表
     * @return
     */
    @Override
    public List<MenuNode> makeNode(List<Menu> menuList) {
        List<MenuNode> menuNodeList = new ArrayList<>();
        if(menuList != null && !menuList.isEmpty()) {
            MenuNode menuNode = null;
            for(Menu menu : menuList) {
                menuNode = makeNode(menu);
                menuNodeList.add(menuNode);
            }
        }else {
            logger.info("MenuManager.makeNode(List<Menu> menuList)异常");
            logger.info("其中menuList为null或empty");
        }
        return menuNodeList;
    }
}
