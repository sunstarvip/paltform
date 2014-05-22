package com.darknight.platform.account.user.service.impl;

import com.darknight.platform.account.user.dao.UserDao;
import com.darknight.platform.account.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
