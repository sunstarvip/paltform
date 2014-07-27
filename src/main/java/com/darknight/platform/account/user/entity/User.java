package com.darknight.platform.account.user.entity;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.role.entity.Role;

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
    private String name;
    private String accountName;
    private String password;
    private String mailAddress;
    private String address;
    private String salt;

    private Integer phoneNum;

    private List<Role> roleList = new ArrayList<>();

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

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
