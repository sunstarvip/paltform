package com.darknight.security.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义ShiroRealm接口
 * Created by DarKnight on 14-2-6.
 */
public interface CustomerShiroRealm {
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals);

    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token);

    public void clearCachedAuthorizationInfo(PrincipalCollection principals);

    public void clearCachedAuthenticationInfo(PrincipalCollection principals);

    public void clearCache(PrincipalCollection principals);
}
