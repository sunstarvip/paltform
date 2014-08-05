package com.darknight.platform.account.user.service;

import com.darknight.platform.account.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface UserService {
    public User find(String userId);

    public List<User> findAll();

    public Page<User> findAll(Pageable page);

    public void flush();

    public User save(User user);

    public List<User> save(List<User> userList);

    public User findByAccountName(String accountName);
}
