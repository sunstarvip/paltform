package com.darknight.platform.account.user.service.impl;

import com.darknight.platform.account.user.dao.UserDao;
import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public User find(String userId) {
        User user = userDao.getOne(userId);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userDao.findAll();
        if(userList == null) {
            userList = new ArrayList<>();
        }
        return userList;
    }

    @Override
    public Page<User> findAll(Pageable page) {
        Page<User> userPage = userDao.findAll(page);
        return userPage;
    }

    @Override
    @Transactional(readOnly = false)
    public void flush() {
        userDao.flush();
    }

    @Override
    @Transactional(readOnly = false)
    public User save(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    @Transactional(readOnly = false)
    public List<User> save(List<User> userList) {
        userList = userDao.save(userList);
        flush();
        return userList;
    }

    @Override
    public User findByAccountName(String accountName) {
        User user = userDao.findByAccountName(accountName);
        return user;
    }
}
