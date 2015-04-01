package com.darknight.platform.system.menu.service.impl;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.system.menu.dao.MenuDao;
import com.darknight.platform.system.menu.entity.Menu;
import com.darknight.platform.system.menu.service.MenuService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by DarKnight on 2015/3/17.
 */
@Service
@Transactional(readOnly = true)
public class MenuManager implements MenuService {
    private MenuDao menuDao;

    @Resource
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    //通用方法区域 Begin

    /**
     * 推送缓存中的数据操作至数据库
     */
    @Override
    public void flush() {
        menuDao.flush();
    }

    /**
     * 保存Menu系统菜单对象
     * @param menu 系统菜单对象
     * @return
     */
    @Override
    public Menu save(Menu menu) {
        return menuDao.saveAndFlush(menu);
    }

    /**
     * 批量保存Menu系统菜单对象
     * @param menuList 系统菜单对象列表
     * @return
     */
    @Override
    public List<Menu> save(List<Menu> menuList) {
        menuList = menuDao.save(menuList);
        flush();
        return menuList;
    }

    /**
     * 删除该系统菜单ID下的Menu系统菜单对象
     * 物理删除
     * @param menuId 系统菜单ID
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(String menuId) {
        menuDao.delete(menuId);
        flush();
    }

    /**
     * 根据传入系统菜单ID, 批量删除Menu系统菜单对象
     * 物理删除
     * @param idList 系统菜单ID列表
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(List<String> idList) {
        for(String menuId : idList) {
            menuDao.delete(menuId);
        }
        flush();
    }

    /**
     * 删除Menu系统菜单对象
     * 物理删除
     * @param menu 系统菜单对象
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(Menu menu) {
        menuDao.delete(menu);
        flush();
    }

    /**
     * 批量删除Menu系统菜单对象
     * 物理删除
     * @param menuList 系统菜单对象列表
     */
    @Override
    @Transactional(readOnly = false)
    public void realDeleteInBatch(List<Menu> menuList) {
        menuDao.deleteInBatch(menuList);
        flush();
    }

    /**
     * 删除所有的Menu系统菜单对象
     * 物理删除
     */
    @Override
    @Transactional(readOnly = false)
    public void realDeleteAll() {
        menuDao.deleteAll();
        flush();
    }

    /**
     * 统计所有Menu系统菜单对象的总数量
     * @return
     */
    @Override
    public long count() {
        return menuDao.count();
    }

    /**
     * 根据传入系统菜单ID, 判断该系统菜单对象是否存在
     * @param menuId 系统菜单ID
     * @return
     */
    @Override
    public boolean exists(String menuId) {
        return menuDao.exists(menuId);
    }

    /**
     * 根据系统菜单ID, 查询Menu系统菜单对象
     * @param menuId 系统菜单ID
     * @return
     */
    @Override
    public Menu find(String menuId) {
        return menuDao.getOne(menuId);
    }

    /**
     * 根据传入的系统菜单ID, 批量查询Menu系统菜单对象
     * @param idList 系统菜单ID列表
     * @return
     */
    @Override
    public List<Menu> find(List<String> idList) {
        List<Menu> menuList = menuDao.findAll(idList);
        if(menuList == null) {
            menuList = new ArrayList<Menu>();
        }
        return menuList;
    }

    /**
     * 查询所有的Menu系统菜单对象
     * @return
     */
    @Override
    public List<Menu> findAll() {
        List<Menu> menuList = menuDao.findAll();
        if(menuList == null) {
            menuList = new ArrayList<Menu>();
        }
        return menuList;
    }

    /**
     * 分页查询所有的Menu系统菜单对象
     * @param page 分页容器
     * @return
     */
    @Override
    public Page<Menu> findAll(Pageable page) {
        Page<Menu> menuPage = menuDao.findAll(page);
        return menuPage;
    }

    /**
     * 查询所有的Menu系统菜单对象, 并根据Sort排序规则进行排序
     * @param sort 排序规则对象
     * @return
     */
    @Override
    public List<Menu> findAll(Sort sort) {
        List<Menu> menuList = menuDao.findAll(sort);
        if(menuList == null) {
            menuList = new ArrayList<Menu>();
        }
        return menuList;
    }

    /**
     * 查询所有未逻辑删除的Menu系统菜单对象
     * @return
     */
    @Override
    public List<Menu> findAllVisible() {
        // 创建查询对象
        Criteria criteria = menuDao.createCriteria();
        // 添加查询规则
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        List<Menu> menuList = criteria.list();
        return menuList;
    }

    /**
     * 删除该系统菜单ID下的Menu系统菜单对象
     * 逻辑删除
     * @param menuId 系统菜单ID
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String menuId) {
        if(exists(menuId)) {
            Menu menu = find(menuId);
            menu.setUpdateTime(new Date());
            menu.setVisibleTag(DefaultEntity.VisibleTag.NO);
            save(menu);
        }
    }

    /**
     * 根据传入系统菜单ID, 批量删除Menu系统菜单对象
     * 逻辑删除
     * @param idList 系统菜单ID列表
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(List<String> idList) {
        List<Menu> menuList = find(idList);
        if(!menuList.isEmpty()) {
            for(Menu menu : menuList) {
                menu.setUpdateTime(new Date());
                menu.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(menuList);
        }
    }

    /**
     * 删除Menu系统菜单对象
     * 逻辑删除
     * @param menu 系统菜单对象
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Menu menu) {
        menu.setUpdateTime(new Date());
        menu.setVisibleTag(DefaultEntity.VisibleTag.NO);
        save(menu);
    }

    /**
     * 批量删除Menu系统菜单对象
     * 逻辑删除
     * @param menuList 系统菜单对象列表
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteInBatch(List<Menu> menuList) {
        if(!menuList.isEmpty()) {
            for(Menu menu : menuList) {
                menu.setUpdateTime(new Date());
                menu.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(menuList);
        }
    }

    /**
     * 删除所有的Menu系统菜单对象
     * 逻辑删除
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteAll() {
        List<Menu> menuList = findAllVisible();
        if(!menuList.isEmpty()) {
            for(Menu menu : menuList) {
                menu.setUpdateTime(new Date());
                menu.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(menuList);
        }
    }
    //通用方法区域 End

    /**
     * 通过条件分页查询系统菜单列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<Menu> findSearchPage(Map<String, Object> searchMap, Pageable page) {
        // 创建查询对象
        Criteria criteria = menuDao.createCriteria();
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
        Page<Menu> menuPage = new PageImpl(criteria.list(), page, totalNum);
        return menuPage;
    }
}
