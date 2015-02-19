package com.darknight.platform.account.user.service.impl;

import com.darknight.platform.account.user.dao.UserDao;
import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by DarKnight on 2014/5/22 0022.
 */
@Service
@Transactional(readOnly = true)
public class UserManager implements UserService {
    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //通用方法区域 Begin
    /**
     * 推送缓存中的数据操作至数据库
     */
    @Override
    @Transactional(readOnly = false)
    public void flush() {
        userDao.flush();
    }

    /**
     * 保存User用户对象
     * @param user
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public User save(User user) {
        return userDao.saveAndFlush(user);
    }

    /**
     * 批量保存User用户对象
     * @param userList
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public List<User> save(List<User> userList) {
        userList = userDao.save(userList);
        flush();
        return userList;
    }

    /**
     * 删除该用户ID下的User用户对象
     * @param userId
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String userId) {
        userDao.delete(userId);
        flush();
    }

    /**
     * 根据传入用户ID, 批量删除User用户对象
     * @param idList
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(List<String> idList) {
        for(String userId : idList) {
            userDao.delete(userId);
        }
        flush();
    }

    /**
     * 删除User用户对象
     * @param user
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(User user) {
        userDao.delete(user);
        flush();
    }

    /**
     * 批量删除User用户对象
     * @param userList
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteInBatch(List<User> userList) {
        userDao.deleteInBatch(userList);
        flush();
    }

    /**
     * 删除所有的User用户对象
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteAll() {
        userDao.deleteAll();
        flush();
    }

    /**
     * 统计所有User用户对象的总数量
     * @return
     */
    @Override
    public long count() {
        return userDao.count();
    }

    /**
     * 根据传入用户ID, 判断该用户是否存在
     * @param userId
     * @return
     */
    @Override
    public boolean exists(String userId) {
        return userDao.exists(userId);
    }

    /**
     * 根据用户ID, 查询User用户对象
     * @param userId
     * @return
     */
    @Override
    public User find(String userId) {
        return userDao.getOne(userId);
    }

    /**
     * 根据传入的用户ID, 批量查询User用户对象
     * @param idList
     * @return
     */
    @Override
    public List<User> find(List<String> idList) {
        List<User> userList = userDao.findAll(idList);
        if(userList == null) {
            userList = new ArrayList<User>();
        }
        return userList;
    }

    /**
     * 查询所有的User用户对象
     * @return
     */
    @Override
    public List<User> findAll() {
        List<User> userList = userDao.findAll();
        if(userList == null) {
            userList = new ArrayList<User>();
        }
        return userList;
    }

    /**
     * 分页查询所有的User用户对象
     * @param page
     * @return
     */
    @Override
    public Page<User> findAll(Pageable page) {
        Page<User> userPage = userDao.findAll(page);
        return userPage;
    }

    /**
     * 查询所有的User用户对象, 并根据Sort排序规则进行排序
     * @param sort
     * @return
     */
    @Override
    public List<User> findAll(Sort sort) {
        List<User> userList = userDao.findAll(sort);
        if(userList == null) {
            userList = new ArrayList<User>();
        }
        return userList;
    }
    //通用方法区域 End

    /**
     * 根据accountName用户登录名, 查询User用户对象
     * @param accountName
     * @return
     */
    @Override
    public User findByAccountName(String accountName) {
        User user = userDao.findByAccountName(accountName);
        return user;
    }
}
