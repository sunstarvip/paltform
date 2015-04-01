package com.darknight.platform.system.menu.service;

import com.darknight.platform.system.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * Created by DarKnight on 2015/3/17.
 */
public interface MenuService {

    //通用方法区域 Begin
    /**
     * 推送缓存中的数据操作至数据库
     */
    public void flush();

    /**
     * 保存Menu系统菜单对象
     * @param menu 系统菜单对象
     * @return
     */
    public Menu save(Menu menu);

    /**
     * 批量保存Menu系统菜单对象
     * @param menuList 系统菜单对象列表
     * @return
     */
    public List<Menu> save(List<Menu> menuList);

    /**
     * 删除该系统菜单ID下的Menu系统菜单对象
     * 物理删除
     * @param menuId 系统菜单ID
     */
    public void realDelete(String menuId);

    /**
     * 根据传入系统菜单ID, 批量删除Menu系统菜单对象
     * 物理删除
     * @param idList 系统菜单ID列表
     */
    public void realDelete(List<String> idList);

    /**
     * 删除Menu系统菜单对象
     * 物理删除
     * @param menu 系统菜单对象
     */
    public void realDelete(Menu menu);

    /**
     * 批量删除Menu系统菜单对象
     * 物理删除
     * @param menuList 系统菜单对象列表
     */
    public void realDeleteInBatch(List<Menu> menuList);

    /**
     * 删除所有的Menu系统菜单对象
     * 物理删除
     */
    public void realDeleteAll();

    /**
     * 统计所有Menu系统菜单对象的总数量
     * @return
     */
    public long count();

    /**
     * 根据传入系统菜单ID, 判断该系统菜单对象是否存在
     * @param menuId 系统菜单ID
     * @return
     */
    public boolean exists(String menuId);

    /**
     * 根据系统菜单ID, 查询Menu系统菜单对象
     * @param menuId 系统菜单ID
     * @return
     */
    public Menu find(String menuId);

    /**
     * 根据传入的系统菜单ID, 批量查询Menu系统菜单对象
     * @param idList 系统菜单ID列表
     * @return
     */
    public List<Menu> find(List<String> idList);

    /**
     * 查询所有的Menu系统菜单对象
     * @return
     */
    public List<Menu> findAll();

    /**
     * 分页查询所有的Menu系统菜单对象
     * @param page 分页容器
     * @return
     */
    public Page<Menu> findAll(Pageable page);

    /**
     * 查询所有的Menu系统菜单对象, 并根据Sort排序规则进行排序
     * @param sort 排序规则对象
     * @return
     */
    public List<Menu> findAll(Sort sort);

    /**
     * 查询所有未逻辑删除的Menu系统菜单对象
     * @return
     */
    public List<Menu> findAllVisible();

    /**
     * 删除该系统菜单ID下的Menu系统菜单对象
     * 逻辑删除
     * @param menuId 系统菜单ID
     */
    public void delete(String menuId);

    /**
     * 根据传入系统菜单ID, 批量删除Menu系统菜单对象
     * 逻辑删除
     * @param idList 系统菜单ID列表
     */
    public void delete(List<String> idList);

    /**
     * 删除Menu系统菜单对象
     * 逻辑删除
     * @param menu 系统菜单对象
     */
    public void delete(Menu menu);

    /**
     * 批量删除Menu系统菜单对象
     * 逻辑删除
     * @param menuList 系统菜单对象列表
     */
    public void deleteInBatch(List<Menu> menuList);

    /**
     * 删除所有的Menu系统菜单对象
     * 逻辑删除
     */
    public void deleteAll();
    //通用方法区域 End

    /**
     * 通过条件分页查询系统菜单列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    public Page<Menu> findSearchPage(Map<String, Object> searchMap, Pageable page);
}
