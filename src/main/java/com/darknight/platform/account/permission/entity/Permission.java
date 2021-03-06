package com.darknight.platform.account.permission.entity;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.role.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Platform平台系统权限对象
 * Created by DarKnight on 2014/4/23 0023.
 */
@Entity
@DynamicInsert()
@DynamicUpdate()
@Table(name = "t_platform_permission")
public class Permission extends DefaultEntity {
    private String name;
    private String description; // 权限描述

    private Permission parent;
    private List<Permission> children = new ArrayList<>();
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

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
//    @OrderBy("createTime")
    public List<Permission> getChildren() {
        return children;
    }

    @JsonIgnore
    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "t_platform_permission_role",
            joinColumns ={@JoinColumn(name = "permission_id", referencedColumnName = "id") },
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    public List<Role> getRoleList() {
        return roleList;
    }

    @JsonIgnore
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
