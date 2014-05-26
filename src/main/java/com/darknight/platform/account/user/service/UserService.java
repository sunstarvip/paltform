package com.darknight.platform.account.user.service;

import com.darknight.platform.account.user.entity.User;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface UserService {
    public User find(String userId);

    public User findByAccountName(String accountName);
}
