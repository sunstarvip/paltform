package com.darknight.account.user.service.impl;

import com.darknight.account.user.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by DarKnight on 14-2-5.
 */
@Component
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
}
