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
    private String description; // 权限描述
    private String available = Available.YES; // 权限是否可用

    private List<Role> roleList;

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
    @JoinColumn(name = "role_id")
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public interface Available {
        public static String YES = "YES";//可用状态
        public static String NO = "NO";//不可用状态
    }
}
