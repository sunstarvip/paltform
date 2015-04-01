package com.darknight.platform.account.user.service;

import com.darknight.core.base.service.BaseService;
import com.darknight.platform.account.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface UserService extends BaseService<User, String> {

    /**
     * 根据accountName用户登录名, 查询User用户对象
     * @param accountName 用户登录名
     * @return
     */
    public User findByAccountName(String accountName);

    /**
     * 通过条件分页查询用户列表
     * @param searchMap 条件Map
     * @param page 分页对象
     * @return
     */
    public Page<User> findSearchPage(Map<String, Object> searchMap, Pageable page);
}
