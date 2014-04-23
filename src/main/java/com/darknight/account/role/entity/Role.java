package com.darknight.account.role.entity;

import com.darknight.account.permission.entity.Permission;
import com.darknight.account.user.entity.User;
import com.darknight.base.entity.DefaultEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 平台角色对象
 * Created by DarKnight on 14-2-6.
 */
@Entity
@Table(name = "t_platform_role")
public class Role extends DefaultEntity {
    private String name;

    private List<User> userList;
    private List<Permission> permissionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
