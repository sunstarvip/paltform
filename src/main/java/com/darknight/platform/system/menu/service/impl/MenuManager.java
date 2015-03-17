package com.darknight.platform.system.menu.service.impl;

import com.darknight.platform.system.menu.dao.MenuDao;
import com.darknight.platform.system.menu.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
}
