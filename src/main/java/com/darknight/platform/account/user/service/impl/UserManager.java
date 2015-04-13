package com.darknight.platform.account.user.service.impl;

import com.darknight.core.base.dao.BaseJpaDao;
import com.darknight.core.base.service.impl.BaseManager;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 * Created by DarKnight on 2014/5/22 0022.
 */
@Service
@Transactional(readOnly = true)
public class UserManager extends BaseManager<User, String> implements UserService {
    private UserDao userDao;

    @Resource
    public void setBaseDao(BaseJpaDao<User, String> baseDao) {
        super.setBaseDao(baseDao);
        this.userDao = (UserDao)baseDao;
    }

    /**
     * 根据accountName用户登录名, 查询User用户对象
     * @param accountName 用户登录名
     * @return
     */
    @Override
    public User findByAccountName(String accountName) {
        // 获取自定义查询对象，查询未逻辑删除的系统菜单对象
        Criteria criteria = getVisibleCriteria();
        criteria.add(Restrictions.eq("accountName", accountName));
        User user = (User)criteria.uniqueResult();
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
        // 获取自定义查询对象，查询未逻辑删除并默认排序的用户对象
        Criteria criteria = getOrderedVisibleCriteria();
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
