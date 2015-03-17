package com.darknight.platform.system.menu.entity;

import com.darknight.core.base.entity.DefaultEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Platform平台系统菜单对象
 * Created by DarKnight on 2015/3/17.
 */
@Entity
@Table(name = "t_platform_menu")
public class Menu extends DefaultEntity {
    private String name;  //菜单名称
    private String type;  //菜单类型

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
