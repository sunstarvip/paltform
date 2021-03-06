package com.darknight.platform.account.role.entity;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.permission.entity.Permission;
import com.darknight.platform.account.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Platform平台系统角色对象
 * Created by DarKnight on 2014/5/22 0022.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
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

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "t_platform_role_user",
        joinColumns ={@JoinColumn(name = "role_id", referencedColumnName = "id") },
        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    public List<User> getUserList() {
        return userList;
    }

    @JsonIgnore
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "t_platform_permission_role",
            joinColumns ={@JoinColumn(name = "role_id", referencedColumnName = "id") },
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
    )
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    @JsonIgnore
    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
