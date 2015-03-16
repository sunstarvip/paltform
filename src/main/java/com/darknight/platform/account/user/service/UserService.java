package com.darknight.platform.account.user.service;

import com.darknight.platform.account.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface UserService {

    //通用方法区域 Begin

    /**
     * 推送缓存中的数据操作至数据库
     */
    public void flush();

    /**
     * 保存User用户对象
     * @param user 用户对象
     * @return
     */
    public User save(User user);

    /**
     * 批量保存User用户对象
     * @param userList 用户对象列表
     * @return
     */
    public List<User> save(List<User> userList);

    /**
     * 删除该用户ID下的User用户对象
     * 物理删除
     * @param userId 用户ID
     */
    public void realDelete(String userId);

    /**
     * 根据传入用户ID, 批量删除User用户对象
     * 物理删除
     * @param idList 用户ID列表
     */
    public void realDelete(List<String> idList);

    /**
     * 删除User用户对象
     * 物理删除
     * @param user 用户对象
     */
    public void realDelete(User user);

    /**
     * 批量删除User用户对象
     * 物理删除
     * @param userList 用户对象列表
     */
    public void realDeleteInBatch(List<User> userList);

    /**
     * 删除所有的User用户对象
     * 物理删除
     */
    public void realDeleteAll();

    /**
     * 统计所有User用户对象的总数量
     * @return
     */
    public long count();

    /**
     * 根据传入用户ID, 判断该用户是否存在
     * @param userId 用户ID
     * @return
     */
    public boolean exists(String userId);

    /**
     * 根据用户ID, 查询User用户对象
     * @param userId 用户ID
     * @return
     */
    public User find(String userId);

    /**
     * 根据传入的用户ID, 批量查询User用户对象
     * @param idList 用户ID列表
     * @return
     */
    public List<User> find(List<String> idList);

    /**
     * 查询所有的User用户对象
     * @return
     */
    public List<User> findAll();

    /**
     * 分页查询所有的User用户对象
     * @param page 分页容器
     * @return
     */
    public Page<User> findAll(Pageable page);

    /**
     * 查询所有的User用户对象, 并根据Sort排序规则进行排序
     * @param sort 排序规则对象
     * @return
     */
    public List<User> findAll(Sort sort);

    /**
     * 查询所有未逻辑删除的User用户对象
     * @return
     */
    public List<User> findAllVisible();

    /**
     * 删除该用户ID下的User用户对象
     * 逻辑删除
     * @param userId 用户ID
     */
    public void delete(String userId);

    /**
     * 根据传入用户ID, 批量删除User用户对象
     * 逻辑删除
     * @param idList 用户ID列表
     */
    public void delete(List<String> idList);

    /**
     * 删除User用户对象
     * 逻辑删除
     * @param user 用户对象
     */
    public void delete(User user);

    /**
     * 批量删除User用户对象
     * 逻辑删除
     * @param userList 用户对象列表
     */
    public void deleteInBatch(List<User> userList);

    /**
     * 删除所有的User用户对象
     * 逻辑删除
     */
    public void deleteAll();
    //通用方法区域 End

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
