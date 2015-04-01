package com.darknight.platform.system.menu.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.platform.system.menu.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<Menu> findSearchPage(Map<String, Object> searchMap, Pageable page);
}
