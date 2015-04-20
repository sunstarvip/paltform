package com.darknight.platform.system.menu.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.platform.system.menu.entity.Menu;
import com.darknight.platform.system.menu.entity.MenuNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by DarKnight on 2015/3/17.
 */
public interface MenuService extends BaseService<Menu, String> {

    /**
     * 通过条件分页查询系统菜单列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    Page<Menu> findSearchPage(Map<String, Object> searchMap, Pageable page);

    /**
     * 查询所有未逻辑删除的顶级系统菜单列表
     * @return
     */
    List<Menu> findAllVisibleTopMenu();

    /**
     * 根据当前系统菜单实体查询所有未逻辑删除的子级系统菜单列表
     * @param menu 系统菜单实体
     * @return
     */
    List<Menu> findVisibleChildren(Menu menu);

    /**
     * 通过系统菜单对象生成对应树型对象
     * @param menu 系统菜单对象
     * @return
     */
    MenuNode makeNode(Menu menu);

    /**
     * 通过系统菜单对象列表生成对应树型对象列表
     * @param menuList 系统菜单对象列表
     * @return
     */
    List<MenuNode> makeNode(List<Menu> menuList);
}
