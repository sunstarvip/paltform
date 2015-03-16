package com.darknight.platform.account.user.service.impl;

import com.darknight.core.base.entity.DefaultEntity;
import com.darknight.platform.account.user.dao.UserDao;
import com.darknight.platform.account.user.entity.User;
import com.darknight.platform.account.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * @param user 用户对象
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public User save(User user) {
        return userDao.saveAndFlush(user);
    }

    /**
     * 批量保存User用户对象
     * @param userList 用户对象列表
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
     * 物理删除
     * @param userId 用户ID
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(String userId) {
        userDao.delete(userId);
        flush();
    }

    /**
     * 根据传入用户ID, 批量删除User用户对象
     * 物理删除
     * @param idList 用户ID列表
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(List<String> idList) {
        for(String userId : idList) {
            userDao.delete(userId);
        }
        flush();
    }

    /**
     * 删除User用户对象
     * 物理删除
     * @param user 用户对象
     */
    @Override
    @Transactional(readOnly = false)
    public void realDelete(User user) {
        userDao.delete(user);
        flush();
    }

    /**
     * 批量删除User用户对象
     * 物理删除
     * @param userList 用户对象列表
     */
    @Override
    @Transactional(readOnly = false)
    public void realDeleteInBatch(List<User> userList) {
        userDao.deleteInBatch(userList);
        flush();
    }

    /**
     * 删除所有的User用户对象
     * 物理删除
     */
    @Override
    @Transactional(readOnly = false)
    public void realDeleteAll() {
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
     * @param userId 用户ID
     * @return
     */
    @Override
    public boolean exists(String userId) {
        return userDao.exists(userId);
    }

    /**
     * 根据用户ID, 查询User用户对象
     * @param userId 用户ID
     * @return
     */
    @Override
    public User find(String userId) {
        return userDao.getOne(userId);
    }

    /**
     * 根据传入的用户ID, 批量查询User用户对象
     * @param idList 用户ID列表
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
     * @param page 分页容器
     * @return
     */
    @Override
    public Page<User> findAll(Pageable page) {
        Page<User> userPage = userDao.findAll(page);
        return userPage;
    }

    /**
     * 查询所有的User用户对象, 并根据Sort排序规则进行排序
     * @param sort 排序规则对象
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

    /**
     * 查询所有未逻辑删除的User用户对象
     * @return
     */
    @Override
    public List<User> findAllVisible() {
        // 创建查询对象
        Criteria criteria = userDao.createCriteria();
        // 添加查询规则
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        List<User> userList = criteria.list();
        return userList;
    }

    /**
     * 删除该用户ID下的User用户对象
     * 逻辑删除
     * @param userId 用户ID
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String userId) {
        if(exists(userId)) {
            User user = find(userId);
            user.setVisibleTag(DefaultEntity.VisibleTag.NO);
            save(user);
        }
    }

    /**
     * 根据传入用户ID, 批量删除User用户对象
     * 逻辑删除
     * @param idList 用户ID列表
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(List<String> idList) {
        List<User> userList = find(idList);
        if(!userList.isEmpty()) {
            for(User user : userList) {
                user.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(userList);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(User user) {
        user.setVisibleTag(DefaultEntity.VisibleTag.NO);
        save(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteInBatch(List<User> userList) {
        if(!userList.isEmpty()) {
            for(User user : userList) {
                user.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(userList);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAll() {
        List<User> userList = findAllVisible();
        if(!userList.isEmpty()) {
            for(User user : userList) {
                user.setVisibleTag(DefaultEntity.VisibleTag.NO);
            }
            save(userList);
        }
    }
    //通用方法区域 End

    /**
     * 根据accountName用户登录名, 查询User用户对象
     * @param accountName 用户登录名
     * @return
     */
    @Override
    public User findByAccountName(String accountName) {
        User user = userDao.findByAccountName(accountName);
        return user;
    }

    /**
     * 通过条件分页查询未逻辑删除的用户列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    @Override
    public Page<User> findSearchPage(Map<String, Object> searchMap, Pageable page) {
        // 创建查询对象
        Criteria criteria = userDao.createCriteria();
        // 添加查询规则
        criteria.add(Restrictions.eq("visibleTag", DefaultEntity.VisibleTag.YES));
        for(Map.Entry<String, Object> searchEntry: searchMap.entrySet()) {
            if(searchEntry.getValue() != null && StringUtils.isNotBlank(searchEntry.getValue().toString())) {
                if(StringUtils.contains(searchEntry.getKey(), "like_")) {
                    criteria.add(Restrictions.like(
                            StringUtils.replace(searchEntry.getKey(), "like_", ""),
                            "%" + searchEntry.getValue() + "%"
                    ));
                }else {
                    criteria.add(Restrictions.eq(searchEntry.getKey(), searchEntry.getValue()));
                }
            }
        }
        //统计数据总数
        criteria.setProjection(Projections.rowCount());
        Object result = (criteria.uniqueResult() != null)?criteria.uniqueResult():0;
        Long totalNum = Long.valueOf(result.toString());
        criteria.setProjection(null);

        //设定查询起始值
        criteria.setFirstResult(page.getOffset());
        //设定查询分页大小
        criteria.setMaxResults(page.getPageSize());
        Page<User> userPage = new PageImpl(criteria.list(), page, totalNum);
        return userPage;
    }
}
