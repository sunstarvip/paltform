package com.darknight.account.user.entity;

import com.darknight.account.role.entity.Role;
import com.darknight.base.entity.DefaultEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 平台用户对象
 * Created by DarKnight on 14-2-5.
 */
@Entity
@Table(name = "t_platform_user")
public class User extends DefaultEntity{
    private String name; // 用户昵称, 非唯一
    private String accountName; // 账户名称, 唯一
    private String password; // 账户密码
    private String userStatus = UserStatus.NORMAL; //用户状态

    private List<Role> roleList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public interface UserStatus {
        public static String NORMAL = "NORMAL";//正常状态
        public static String LOCKED = "LOCKED";//已锁状态
    }
}
