package com.darknight.security.shiro.realm.impl;

import com.darknight.security.shiro.realm.CustomerShiroRealm;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * 默认自定义ShiroRealm
 * Created by DarKnight on 14-2-6.
 */
@Component
public class DefaultShiroRealmImpl implements CustomerShiroRealm {
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        return null;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {

    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {

    }

    @Override
    public void clearCache(PrincipalCollection principals) {

    }
}
