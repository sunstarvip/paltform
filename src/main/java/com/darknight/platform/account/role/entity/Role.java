package com.darknight.platform.account.role.entity;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.user.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Platform平台系统角色对象
 * Created by DarKnight on 2014/5/22 0022.
 */
@Entity
@Table(name = "t_platform_role")
public class Role extends DefaultEntity {
    private String name;
    private String description; // 角色描述

    private List<User> userList = new ArrayList<>();
    private List<Permission> permissionList = new ArrayList<>();

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
    @JoinTable(name = "t_platform_role_user",
        joinColumns ={@JoinColumn(name = "role_id", referencedColumnName = "id") },
        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @ManyToMany(mappedBy = "roleList")
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
