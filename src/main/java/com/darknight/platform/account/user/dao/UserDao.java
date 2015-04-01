package com.darknight.platform.account.user.dao;

import com.darknight.core.base.dao.BaseJpaDao;
//import com.darknight.core.base.dao.BaseJpaRepository;
import com.darknight.platform.account.user.entity.User;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface UserDao extends BaseJpaDao<User, String> {
    /**
     * 通过账户名查询用户对象
     * @param accountName 账户名
     * @return
     */
    public User findByAccountName(String accountName);
}
