package com.darknight.platform.account.role.dao;

import com.darknight.core.base.dao.BaseJpaRepository;
import com.darknight.platform.account.role.entity.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * Created by DarKnight on 2014/5/22 0022.
 */
public interface RoleDao extends BaseJpaRepository<Role, String> {
}
