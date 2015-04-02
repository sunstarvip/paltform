package com.darknight.platform.system.menu.entity;

import com.darknight.core.base.entity.TreeEntity;

/**
 * Platform平台系统菜单树型对象
 * 树型实体对象TreeEntity中text属性对应
 * 系统菜单对象Menu中name属性
 * Created by DarKnight on 2015/4/1.
 */
public class MenuNode extends TreeEntity {

    /**
     * 菜单URL
     */
    private String urlPath;  // 菜单URL

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
