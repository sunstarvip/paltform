package com.darknight.account.user.dao;

import com.darknight.base.dao.DefaultJpaRepository;
import com.darknight.account.user.entity.User;

/**
 * Created by DarKnight on 14-2-5.
 */
public interface UserDao extends DefaultJpaRepository<User, String> {
    /**
     * 通过唯一的账户名称查询对应的用户对象
     * @param accountName
     * @return
     */
    User findByAccountName(String accountName);
}
