package com.darknight.account.permission.entity;

import com.darknight.account.role.entity.Role;
import com.darknight.base.entity.DefaultEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by DarKnight on 2014/4/23 0023.
 */
@Entity
@Table(name = "t_platform_permission")
public class Permission extends DefaultEntity {
    private String name;
    private List<Role> roleList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
