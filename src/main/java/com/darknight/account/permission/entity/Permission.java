package com.darknight.account.permission.entity;

import com.darknight.base.entity.DefaultEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by DarKnight on 2014/4/23 0023.
 */
@Entity
@Table(name = "t_platform_permission")
public class Permission extends DefaultEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
