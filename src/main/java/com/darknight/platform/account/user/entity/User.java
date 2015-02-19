package com.darknight.platform.account.user.entity;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.role.entity.Role;
import com.darknight.platform.security.shiro.util.ShiroPasswordUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Platform平台系统用户对象
 * Created by DarKnight on 2014/5/22 0022.
 */
@Entity
@Table(name = "t_platform_user")
public class User extends DefaultEntity {
    private String name;  //用户昵称
    private String accountName;  //账户名称
    private String password;  //密码
    private String mailAddress;  //邮箱地址
    private String address;  //联系地址
    private String salt = ShiroPasswordUtil.getSalt();  //加密salt

    private Integer phoneNum;  //联系电话

    private List<Role> roleList = new ArrayList<>();  //角色列表

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

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    @ManyToMany(mappedBy = "userList")
    public List<Role> getRoleList() {
        return roleList;
    }

    @JsonIgnore
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
