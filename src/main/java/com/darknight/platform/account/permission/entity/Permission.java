package com.darknight.platform.account.permission.entity;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.role.entity.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Platform平台系统权限对象
 * Created by DarKnight on 2014/4/23 0023.
 */
@Entity
@Table(name = "t_platform_permission")
public class Permission extends DefaultEntity {
    private String name;
    private String description; // 权限描述

    private List<Role> roleList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "t_platform_permission_role",
            joinColumns ={@JoinColumn(name = "permission_id", referencedColumnName = "id") },
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
