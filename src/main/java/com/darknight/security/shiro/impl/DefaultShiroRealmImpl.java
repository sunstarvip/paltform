package com.darknight.security.shiro.impl;

import com.darknight.account.user.dao.UserDao;
import com.darknight.security.shiro.CustomerShiroRealm;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认自定义ShiroRealm
 * Created by DarKnight on 14-2-6.
 */
public class DefaultShiroRealmImpl implements CustomerShiroRealm {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
