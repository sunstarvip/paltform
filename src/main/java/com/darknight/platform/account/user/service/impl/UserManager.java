package com.darknight.platform.account.user.service.impl;

import com.darknight.platform.account.user.dao.UserDao;
import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
@Component
@Transactional(readOnly = true)
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByAccountName(String accountName) {
        User user = userDao.findByAccountName(accountName);
        return user;
    }
}
