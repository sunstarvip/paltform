package com.darknight.account.user.entity;

import com.darknight.base.entity.DefaultEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 平台用户对象
 * Created by DarKnight on 14-2-5.
 */
@Entity
@Table(name = "t_platform_user")
public class User extends DefaultEntity{
    private String name;
    private String accountName;
    private String password;

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
}
