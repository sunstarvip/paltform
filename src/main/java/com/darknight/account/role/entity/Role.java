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
    private String name; // 角色名称
    private String description; // 角色描述
    private String available = Available.YES; // 角色是否可用

    private List<User> userList;
    private List<Permission> permissionList;

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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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

    public interface Available {
        public static String YES = "YES";//可用状态
        public static String NO = "NO";//不可用状态
    }
}
