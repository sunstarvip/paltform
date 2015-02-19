package com.darknight.platform.account.user.service;

import com.darknight.platform.account.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface UserService {

    //通用方法区域 Begin
    public void flush();

    public User save(User user);

    public List<User> save(List<User> userList);

    public void delete(String userId);

    public void delete(List<String> idList);

    public void delete(User user);

    public void deleteInBatch(List<User> userList);

    public void deleteAll();

    public long count();

    public boolean exists(String userId);

    public User find(String userId);

    public List<User> find(List<String> idList);

    public List<User> findAll();

    public Page<User> findAll(Pageable page);

    public List<User> findAll(Sort sort);
    //通用方法区域 End

    public User findByAccountName(String accountName);
}
