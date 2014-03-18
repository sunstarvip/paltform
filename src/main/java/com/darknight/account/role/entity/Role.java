package com.darknight.account.role.entity;

import com.darknight.base.entity.DefaultEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 平台角色对象
 * Created by DarKnight on 14-2-6.
 */
@Entity
@Table(name = "t_platform_role")
public class Role extends DefaultEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
